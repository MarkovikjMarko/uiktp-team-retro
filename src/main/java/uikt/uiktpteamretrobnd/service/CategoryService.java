package uikt.uiktpteamretrobnd.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uikt.uiktpteamretrobnd.model.Category;
import uikt.uiktpteamretrobnd.model.Retrospective;
import uikt.uiktpteamretrobnd.model.exceptions.ModelNotFoundException;
import uikt.uiktpteamretrobnd.repository.CategoryRepository;
import uikt.uiktpteamretrobnd.repository.RetrospectiveRepository;
import uikt.uiktpteamretrobnd.web.requests.CategoryRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final RetrospectiveRepository retrospectiveRepository;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository, RetrospectiveRepository retrospectiveRepository) {
        this.categoryRepository = categoryRepository;
        this.retrospectiveRepository = retrospectiveRepository;
    }


    public List<Category> findAll()
    {
        return categoryRepository.findAll();
    }

    public Optional<Category> find(Long id)
    {
        return categoryRepository.findById(id);
    }


    public Category create(CategoryRequest categoryRequest) throws IOException {
        Retrospective retro = this.retrospectiveRepository.findById(categoryRequest.getRetrospectiveId()).orElseThrow(ModelNotFoundException::new);
        String name = categoryRequest.getName();
        String description = categoryRequest.getDescription();
        String imageName = this.uploadImage(categoryRequest.getImage());

        Category category = new Category(name,description,retro, imageName);

        return categoryRepository.save(category);
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

    public Category update(Long id, CategoryRequest categoryRequest) throws IOException {
        Category category = this.categoryRepository.findById(id).orElseThrow(ModelNotFoundException::new);

        String name = categoryRequest.getName();
        String description = categoryRequest.getDescription();
        Long retrospectiveId = categoryRequest.getRetrospectiveId();

        if(name != null){
            category.setName(name);
        }

        if(description != null){
            category.setDescription(description);
        }

        if(retrospectiveId != null){
            Retrospective retrospective = this.retrospectiveRepository.findById(retrospectiveId).orElseThrow(ModelNotFoundException::new);
            category.setRetrospective(retrospective);
        }

        if(categoryRequest.getImage() != null){
            String imageName = this.uploadImage(categoryRequest.getImage());
            category.setImageName(imageName);
        }

       return categoryRepository.save(category);
    }

    public boolean delete(Long id)
    {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
