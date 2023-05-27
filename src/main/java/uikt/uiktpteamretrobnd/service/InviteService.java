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
import uikt.uiktpteamretrobnd.web.dto.InviteDTO;
import uikt.uiktpteamretrobnd.web.requests.InviteRequest;

import java.util.ArrayList;
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

    public Invite createSingleInvite(InviteRequest inviteRequest) {
        Retrospective retrospective = this.retrospectiveRepository.findById(inviteRequest.getRetrospectiveId()).get();
        User user = this.userRepository.findById(inviteRequest.getUserId()).get();

        Invite invite = new Invite(retrospective, user);

        return this.inviteRepository.save(invite);
    }

    public List<InviteDTO> createTeamInvites(InviteRequest inviteRequest){
        Retrospective retrospective = this.retrospectiveRepository.findById(inviteRequest.getRetrospectiveId()).get();

        Team team = this.teamRepository.findById(inviteRequest.getUserId()).get();

        List<InviteDTO> inviteDTOS = new ArrayList<>();

        for (User user : team.getUsers()) {
            Invite invite = new Invite(retrospective, user);
            this.inviteRepository.save(invite);

            inviteDTOS.add(new InviteDTO(invite));
        }

        return inviteDTOS;
    }

    public boolean delete(Long id) {
        if (inviteRepository.existsById(id)) {
            inviteRepository.deleteById(id);

            return true;
        }
        return false;
    }
}
