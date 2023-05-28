package uikt.uiktpteamretrobnd.seeder;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {

    private final TemplateSeeder templateSeeder;
    private final UserSeeder userSeeder;
    private final TeamSeeder teamSeeder;
    private final RetrospectiveSeeder retrospectiveSeeder;
    private final ItemSeeder itemSeeder;

    public DatabaseSeeder(TemplateSeeder templateSeeder, UserSeeder userSeeder, TeamSeeder teamSeeder, RetrospectiveSeeder retrospectiveSeeder, ItemSeeder itemSeeder) {
        this.templateSeeder = templateSeeder;
        this.userSeeder = userSeeder;
        this.teamSeeder = teamSeeder;
        this.retrospectiveSeeder = retrospectiveSeeder;
        this.itemSeeder = itemSeeder;
    }

    @PostConstruct
    public void seeder() {
        this.templateSeeder.seed();
        this.userSeeder.seedRandomUsers();
        this.teamSeeder.seed();
        this.retrospectiveSeeder.seed();
        this.itemSeeder.seed();
    }
}
