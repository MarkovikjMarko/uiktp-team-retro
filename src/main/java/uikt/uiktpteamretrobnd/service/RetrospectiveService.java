package uikt.uiktpteamretrobnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uikt.uiktpteamretrobnd.model.Retrospective;
import uikt.uiktpteamretrobnd.repository.RetrospectiveRepository;

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

    public Retrospective create(Retrospective retrospective) {
        return retrospectiveRepository.save(retrospective);
    }

    public Optional<Retrospective> update(Long id, Retrospective retrospective) {
        Optional<Retrospective> existingRetrospective = retrospectiveRepository.findById(id);

        if (existingRetrospective.isPresent()) {
            return Optional.of(retrospectiveRepository.save(retrospective));
        }

        return Optional.empty();
    }

    public boolean delete(Long id) {
        if (retrospectiveRepository.existsById(id)) {
            retrospectiveRepository.deleteById(id);

            return true;
        }

        return false;
    }
}