package uikt.uiktpteamretrobnd.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Lazy;
import uikt.uiktpteamretrobnd.enums.RetrospectiveStatus;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Retrospective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate date;

    private String sprintName;

    @Enumerated(EnumType.STRING)
    private RetrospectiveStatus status = RetrospectiveStatus.DEFAULT;

    @OneToMany(mappedBy = "retrospective", fetch = FetchType.LAZY)
    private List<Category> categories;

    @OneToMany(mappedBy = "retrospective")
    private List<Invite> invites;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "creator_id")
    @Lazy
    private User creator;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "team_id")
    private Team team;

    public Retrospective() {
    }

    public Retrospective(String title, LocalDate date, String sprintName, RetrospectiveStatus status, User creator, Team team) {
        this.title = title;
        this.date = date;
        this.sprintName = sprintName;
        this.status = status == null ? RetrospectiveStatus.DEFAULT : status;
        this.creator = creator;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public RetrospectiveStatus getStatus() {
        return status;
    }

    public void setStatus(RetrospectiveStatus status) {
        this.status = status;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}