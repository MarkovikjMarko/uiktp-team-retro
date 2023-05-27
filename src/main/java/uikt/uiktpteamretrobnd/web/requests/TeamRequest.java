package uikt.uiktpteamretrobnd.web.requests;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import uikt.uiktpteamretrobnd.model.User;

import java.util.List;

public class TeamRequest {
    private String name;

    @ManyToOne
    private User leader;

    @OneToMany
    private List<User> users;

    public TeamRequest(String name, User leader) {
        this.name = name;
        this.leader = leader;
    }

    public TeamRequest() {

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
