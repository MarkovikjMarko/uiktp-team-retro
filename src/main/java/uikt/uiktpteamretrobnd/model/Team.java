package uikt.uiktpteamretrobnd.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private User leader;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "team")
    private List<User> users;

    public Team() {

    }

    public Team(Long id, String name, User leader) {
        this.id = id;
        this.name = name;
        this.leader = leader;
    }

    public Team(String name, User leader) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
