package uikt.uiktpteamretrobnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uikt.uiktpteamretrobnd.enums.RetrospectiveStatus;
import uikt.uiktpteamretrobnd.model.Retrospective;
import uikt.uiktpteamretrobnd.model.User;
import uikt.uiktpteamretrobnd.model.exceptions.ModelNotFoundException;
import uikt.uiktpteamretrobnd.repository.RetrospectiveRepository;
import uikt.uiktpteamretrobnd.repository.UserRepository;
import uikt.uiktpteamretrobnd.web.requests.RetrospectiveRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RetrospectiveService {
    private final RetrospectiveRepository retrospectiveRepository;

    private final UserRepository userRepository;

    @Autowired
    public RetrospectiveService(RetrospectiveRepository retrospectiveRepository, UserRepository userRepository) {
        this.retrospectiveRepository = retrospectiveRepository;
        this.userRepository = userRepository;
    }

    public List<Retrospective> findAll() {
        return retrospectiveRepository.findAll();
    }

    public Optional<Retrospective> find(Long id) {
        return retrospectiveRepository.findById(id);
    }

    public Retrospective create(RetrospectiveRequest retrospectiveRequest) {
        String title = retrospectiveRequest.getTitle();
        LocalDate date = retrospectiveRequest.getDate();
        String sprintName = retrospectiveRequest.getSprintName();
        RetrospectiveStatus status = retrospectiveRequest.getStatus();

        User creator = this.userRepository.findById(retrospectiveRequest.getCreatorId()).orElseThrow(ModelNotFoundException::new);

        Retrospective retrospective = new Retrospective(title, date, sprintName, status, creator);

        retrospectiveRepository.save(retrospective);

        return retrospective;
    }

    public Retrospective update(Long id, RetrospectiveRequest retrospectiveRequest) {
        Retrospective retrospective = this.retrospectiveRepository.findById(id).orElseThrow(ModelNotFoundException::new);

        String title = retrospectiveRequest.getTitle();
        LocalDate date = retrospectiveRequest.getDate();
        String sprintName = retrospectiveRequest.getSprintName();
        RetrospectiveStatus status = retrospectiveRequest.getStatus();
        Long creatorId = retrospectiveRequest.getCreatorId();

        if(title != null){
            retrospective.setTitle(title);
        }

        if(date != null){
            retrospective.setDate(date);
        }

        if(sprintName != null){
            retrospective.setSprintName(sprintName);
        }

        if(status != null){
            retrospective.setStatus(status);
        }

        if(creatorId != null){
            User creator = this.userRepository.findById(retrospectiveRequest.getCreatorId()).orElseThrow(ModelNotFoundException::new);
            retrospective.setCreator(creator);
        }

        retrospectiveRepository.save(retrospective);

        return retrospective;
    }

    public boolean delete(Long id) {
        if (retrospectiveRepository.existsById(id)) {
            retrospectiveRepository.deleteById(id);

            return true;
        }

        return false;
    }
}