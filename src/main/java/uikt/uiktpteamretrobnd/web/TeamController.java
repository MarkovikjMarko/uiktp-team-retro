package uikt.uiktpteamretrobnd.web;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import uikt.uiktpteamretrobnd.model.Retrospective;
import uikt.uiktpteamretrobnd.model.Team;
import uikt.uiktpteamretrobnd.service.TeamService;
import uikt.uiktpteamretrobnd.web.requests.RetrospectiveRequest;
import uikt.uiktpteamretrobnd.web.requests.TeamRequest;
import uikt.uiktpteamretrobnd.web.response.ApiResponse;
import uikt.uiktpteamretrobnd.web.response.CustomResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService service;
    private final CustomResponse response;


    public TeamController(TeamService service, CustomResponse response) {
        this.service = service;
        this.response = response;
    }

    @GetMapping
    public ApiResponse<List<Team>> index() {
        List<Team> teams = service.findAll();

        return this.response.success(teams);
    }

    @GetMapping("/{id}")
    public ApiResponse<Team> show(@PathVariable("id") Long id) {
        Optional<Team> team = service.find(id);

        if (team.isPresent()) {
            return this.response.success(team);
        } else {
            return this.response.notFound(team);
        }
    }

    @PostMapping
    public ApiResponse<Team> create(@RequestBody @Valid TeamRequest teamRequest) {
        Team team = service.create(new TeamRequest());

        return this.response.created(team);
    }

    @PutMapping("/{id}")
    public ApiResponse<Team> update(@PathVariable("id") Long id, @RequestBody TeamRequest teamRequest) {
        Team team = service.update(id, new TeamRequest());

        return this.response.success(team);
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
