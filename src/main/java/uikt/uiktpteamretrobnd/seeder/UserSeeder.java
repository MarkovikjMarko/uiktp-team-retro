package uikt.uiktpteamretrobnd.seeder;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import uikt.uiktpteamretrobnd.model.Team;
import uikt.uiktpteamretrobnd.model.User;
import uikt.uiktpteamretrobnd.repository.UserRepository;

import java.util.Random;

@Component
public class UserSeeder {
    private final UserRepository userRepository;

    public UserSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final String DEFAULT_IMAGE_NAME = "Unknown_person.jpg";
    private final String DEFAULT_PASSWORD = "password";

    public void seedRandomUsers() {
        Faker faker = new Faker();

        for (int i = 0; i < new Random().nextInt(5 - 3 + 1) + 3; i++) {
            String name = faker.name().fullName();
            String email = faker.internet().emailAddress();
            String password = DEFAULT_PASSWORD;
            String imageName = DEFAULT_IMAGE_NAME;
            Team team = null;

            User user = new User(name, email, password, imageName, team);

            userRepository.save(user);
        }
    }

    public void seedUsersForTeam(Team team) {
        Faker faker = new Faker();

        for (int i = 0; i < new Random().nextInt(3) + 1; i++) {
            String name = faker.name().fullName();
            String email = faker.internet().emailAddress();
            String password = DEFAULT_PASSWORD;
            String imageName = DEFAULT_IMAGE_NAME;

            User user = new User(name, email, password, imageName, team);

            userRepository.save(user);
        }
    }
}
