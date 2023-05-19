package uikt.uiktpteamretrobnd.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private User leader;

    @OneToMany
    private List<User> users;

    public Team() {

    }

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
