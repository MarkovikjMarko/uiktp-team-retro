package uikt.uiktpteamretrobnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uikt.uiktpteamretrobnd.enums.RetrospectiveStatus;
import uikt.uiktpteamretrobnd.model.Retrospective;
import uikt.uiktpteamretrobnd.model.exceptions.ModelNotFoundException;
import uikt.uiktpteamretrobnd.repository.RetrospectiveRepository;
import uikt.uiktpteamretrobnd.web.requests.RetrospectiveRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RetrospectiveService {
    private final RetrospectiveRepository retrospectiveRepository;

    @Autowired
    public RetrospectiveService(RetrospectiveRepository retrospectiveRepository) {
        this.retrospectiveRepository = retrospectiveRepository;
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

        Retrospective retrospective = new Retrospective(title, date, sprintName, status);

        retrospectiveRepository.save(retrospective);

        return retrospective;
    }

    public Retrospective update(Long id, RetrospectiveRequest retrospectiveRequest) {
        Retrospective retrospective = this.retrospectiveRepository.findById(id).orElseThrow(ModelNotFoundException::new);

        String title = retrospectiveRequest.getTitle();
        LocalDate date = retrospectiveRequest.getDate();
        String sprintName = retrospectiveRequest.getSprintName();
        RetrospectiveStatus status = retrospectiveRequest.getStatus();

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