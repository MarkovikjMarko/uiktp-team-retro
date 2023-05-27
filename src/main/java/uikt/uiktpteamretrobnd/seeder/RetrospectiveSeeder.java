package uikt.uiktpteamretrobnd.seeder;

import org.springframework.stereotype.Component;
import uikt.uiktpteamretrobnd.enums.RetrospectiveStatus;
import uikt.uiktpteamretrobnd.model.*;
import uikt.uiktpteamretrobnd.repository.*;
import uikt.uiktpteamretrobnd.service.RetrospectiveService;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class RetrospectiveSeeder {
    private final RetrospectiveRepository retrospectiveRepository;
    private final TeamRepository teamRepository;
    private final TemplateRepository templateRepository;
    private final RetrospectiveService retrospectiveService;
    private final InviteRepository inviteRepository;
    private final UserRepository userRepository;

    public RetrospectiveSeeder(RetrospectiveRepository retrospectiveRepository, TeamRepository teamRepository, TemplateRepository templateRepository, RetrospectiveService retrospectiveService, InviteRepository inviteRepository, UserRepository userRepository) {
        this.retrospectiveRepository = retrospectiveRepository;
        this.teamRepository = teamRepository;
        this.templateRepository = templateRepository;
        this.retrospectiveService = retrospectiveService;
        this.inviteRepository = inviteRepository;
        this.userRepository = userRepository;
    }

    public void seed(){
        List<Template> templates = this.templateRepository.findAll();

        for(int i = 0; i < new Random().nextInt(5 - 3 + 1) + 3; i++){
            Team team = teamRepository.randomTeam();

            Retrospective retrospective = new Retrospective("Retrospective" + i, LocalDate.now(), "Sprint" + i, RetrospectiveStatus.DEFAULT, team.getLeader(), team);
            this.retrospectiveRepository.save(retrospective);

            /* Creates categories for retrospective */
            for(Template template: templates){
                retrospectiveService.createRetrospectiveFromTemplate(retrospective, template.getId());
            }

            /* Creates team member invites to repository */
            for(User user: userRepository.findByTeam(team)){
                Invite invite = new Invite(retrospective, user);
                this.inviteRepository.save(invite);
            }
        }
    }
}
