package uikt.uiktpteamretrobnd.service;

import org.springframework.stereotype.Service;
import uikt.uiktpteamretrobnd.model.Invite;
import uikt.uiktpteamretrobnd.model.Retrospective;
import uikt.uiktpteamretrobnd.model.Team;
import uikt.uiktpteamretrobnd.model.User;
import uikt.uiktpteamretrobnd.repository.InviteRepository;
import uikt.uiktpteamretrobnd.repository.RetrospectiveRepository;
import uikt.uiktpteamretrobnd.repository.TeamRepository;
import uikt.uiktpteamretrobnd.repository.UserRepository;
import uikt.uiktpteamretrobnd.web.requests.InviteRequest;

import java.util.List;
import java.util.Optional;

@Service
public class InviteService {
    private final InviteRepository inviteRepository;
    private final RetrospectiveRepository retrospectiveRepository;
    private final UserRepository userRepository;
    private TeamRepository teamRepository;

    public InviteService(InviteRepository inviteRepository, RetrospectiveRepository retrospectiveRepository, UserRepository userRepository, TeamRepository teamRepository) {
        this.inviteRepository = inviteRepository;
        this.retrospectiveRepository = retrospectiveRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    public List<Invite> findAll() {
        return inviteRepository.findAll();
    }

    public Optional<Invite> find(Long id) {
        return inviteRepository.findById(id);
    }

    public Invite create(InviteRequest inviteRequest) {
        Invite invite = new Invite();
        Retrospective retrospective = this.retrospectiveRepository.findById(inviteRequest.getRetrospectiveId()).get();

        if (inviteRequest.getTeamId() != null) {
            Team team = this.teamRepository.findById(inviteRequest.getUserId()).get();

            Invite invite1 = null;

            for (User user : team.getUsers()) {
                Invite invite2 = new Invite(retrospective, user);
                invite1 = this.inviteRepository.save(invite2);
            }

            return invite1;
        }

        User user = this.userRepository.findById(inviteRequest.getUserId()).get();
        invite.setUser(user);

        return this.inviteRepository.save(invite);
    }

    public boolean delete(Long id) {
        if (inviteRepository.existsById(id)) {
            inviteRepository.deleteById(id);

            return true;
        }
        return false;
    }
}
