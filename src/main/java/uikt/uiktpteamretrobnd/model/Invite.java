package uikt.uiktpteamretrobnd.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Invite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "retrospective_id")
    private Retrospective retrospective;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Invite() {
    }

    public Invite(Retrospective retrospective, User user) {
        this.retrospective = retrospective;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Retrospective getRetrospective() {
        return retrospective;
    }

    public void setRetrospective(Retrospective retrospective) {
        this.retrospective = retrospective;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
