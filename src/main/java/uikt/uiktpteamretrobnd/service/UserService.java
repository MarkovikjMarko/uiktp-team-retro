package uikt.uiktpteamretrobnd.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uikt.uiktpteamretrobnd.model.Team;
import uikt.uiktpteamretrobnd.model.User;
import uikt.uiktpteamretrobnd.model.exceptions.ModelNotFoundException;
import uikt.uiktpteamretrobnd.repository.TeamRepository;
import uikt.uiktpteamretrobnd.repository.UserRepository;
import uikt.uiktpteamretrobnd.web.requests.UserRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;
    private TeamRepository teamRepository;

    public UserService(UserRepository userRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> find(Long id) {
        return userRepository.findById(id);
    }

    public User create(UserRequest userRequest) throws IOException {
        String name = userRequest.getName();
        String password = userRequest.getPassword();
        String email = userRequest.getEmail();
        MultipartFile image = userRequest.getImage();
        Team team = teamRepository.findById(userRequest.getTeamId()).get();

        String imageUrl = this.uploadImage(image);

        User user = new User(name, email, password, imageUrl, team);

        return this.userRepository.save(user);
    }

    public String uploadImage(MultipartFile image) throws IOException {
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Image file is required");
        }

        String imageName = UUID.randomUUID().toString() + StringUtils.cleanPath(image.getOriginalFilename());

        String uploadDir = "src/main/images/uploadedImages/";

        // Create the directory if it doesn't exist
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Path filePath = Path.of(uploadDir + imageName);
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return imageName;
    }

    public User update(Long id, UserRequest userRequest) throws IOException {
        User user = userRepository.findById(id).orElseThrow(ModelNotFoundException::new);

        String name = userRequest.getName();
        String password = userRequest.getPassword();
        String email = userRequest.getEmail();
        MultipartFile image = userRequest.getImage();
        Long teamId = userRequest.getTeamId();

        if (name != null) {
            user.setName(name);
        }

        if (password != null) {
            user.setPassword(password);
        }

        if (email != null) {
            user.setEmail(email);
        }

        if (image != null) {
            String imageName = this.uploadImage(image);
            user.setImageName(imageName);
        }

        if(teamId != null){
            Team team = teamRepository.findById(teamId).get();
            user.setTeam(team);
        }

        return this.userRepository.save(user);
    }

    public boolean delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
