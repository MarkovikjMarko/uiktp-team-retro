package uikt.uiktpteamretrobnd.seeder;

import org.springframework.stereotype.Component;
import uikt.uiktpteamretrobnd.model.Team;
import uikt.uiktpteamretrobnd.model.User;
import uikt.uiktpteamretrobnd.repository.TeamRepository;
import uikt.uiktpteamretrobnd.repository.UserRepository;

import java.util.Random;

@Component
public class TeamSeeder {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final UserSeeder userSeeder;

    public TeamSeeder(TeamRepository teamRepository, UserRepository userRepository, UserSeeder userSeeder) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.userSeeder = userSeeder;
    }

    public void seed(){
        for(int i = 0; i < new Random().nextInt(5 - 3 + 1) + 3; i++){
            User leader = this.userRepository.randomUser();

            Team team = new Team("Team" + i, leader);
            this.teamRepository.save(team);

            userSeeder.seedUsersForTeam(team);
        }
    }
}
