package uikt.uiktpteamretrobnd.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uikt.uiktpteamretrobnd.model.Retrospective;
import uikt.uiktpteamretrobnd.service.RetrospectiveService;
import uikt.uiktpteamretrobnd.web.requests.RetrospectiveRequest;
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
    public ApiResponse<Retrospective> create(@RequestBody @Valid RetrospectiveRequest retrospectiveRequest) {
        Retrospective retrospective = service.create(retrospectiveRequest);

        return this.response.created(retrospective);
    }

    @PutMapping("/{id}")
    public ApiResponse<Retrospective> update(@PathVariable("id") Long id, @RequestBody RetrospectiveRequest retrospectiveRequest) {
        Retrospective retrospective = service.update(id, retrospectiveRequest);

        return this.response.success(retrospective);
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