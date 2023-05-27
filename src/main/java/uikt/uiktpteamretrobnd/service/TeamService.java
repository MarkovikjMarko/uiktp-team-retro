package uikt.uiktpteamretrobnd.service;

import org.springframework.stereotype.Service;
import uikt.uiktpteamretrobnd.model.Team;
import uikt.uiktpteamretrobnd.model.User;
import uikt.uiktpteamretrobnd.model.exceptions.ModelNotFoundException;
import uikt.uiktpteamretrobnd.repository.TeamRepository;
import uikt.uiktpteamretrobnd.web.requests.TeamRequest;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Optional<Team> find(Long id) {
        return teamRepository.findById(id);
    }

    public Team create(TeamRequest teamRequest) {
        String name = teamRequest.getName();
        User leader = teamRequest.getLeader();
        Team team = new Team(name, leader);

        teamRepository.save(team);

        return team;
    }

    public Team update(Long id, TeamRequest teamRequest) {
        Team team = this.teamRepository.findById(id).orElseThrow(ModelNotFoundException::new);
        String name = teamRequest.getName();
        User leader = teamRequest.getLeader();

        if(name != null){
            team.setName(name);
        }

        if(leader != null){
            team.setLeader(leader);
        }

        teamRepository.save(team);

        return team;
    }

    public boolean delete(Long id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
