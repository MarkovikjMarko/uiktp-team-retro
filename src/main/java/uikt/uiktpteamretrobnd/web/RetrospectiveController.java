package uikt.uiktpteamretrobnd.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uikt.uiktpteamretrobnd.model.Retrospective;
import uikt.uiktpteamretrobnd.service.RetrospectiveService;
import uikt.uiktpteamretrobnd.web.response.ApiResponse;
import uikt.uiktpteamretrobnd.web.response.CustomResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/retrospectives")
public class RetrospectiveController extends ResponseEntityExceptionHandler {

    private final RetrospectiveService service;

    private final CustomResponse response;

    @Autowired
    public RetrospectiveController(RetrospectiveService retrospectiveService, CustomResponse response) {
        this.service = retrospectiveService;
        this.response = response;
    }

    @GetMapping
    public ApiResponse<List<Retrospective>> index() {
        List<Retrospective> retrospectives = service.findAll();

        return this.response.success(retrospectives);
    }

    @GetMapping("/{id}")
    public ApiResponse<Retrospective> show(@PathVariable("id") Long id) {
        Optional<Retrospective> retrospective = service.find(id);

        if (retrospective.isPresent()) {
            return this.response.success(retrospective);
        } else {
            return this.response.notFound(retrospective);
        }
    }

    @PostMapping
    public ApiResponse<Retrospective> create(@RequestBody @Valid Retrospective retrospective) {
        Retrospective createdRetrospective = service.create(retrospective);

        return this.response.created(createdRetrospective);
    }

    @PutMapping("/{id}")
    public ApiResponse<Retrospective> update(@PathVariable("id") Long id, @RequestBody Retrospective retrospective) {
        Optional<Retrospective> updatedRetrospective = service.update(id, retrospective);

        if (updatedRetrospective.isPresent()) {
            return this.response.success(retrospective);
        } else {
            return this.response.notFound(retrospective);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") Long id) {
        boolean deleted = service.delete(id);

        if (deleted) {
            return response.deleted();
        } else {
            return this.response.notFound(null);
        }
    }
}