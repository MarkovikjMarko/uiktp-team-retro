package uikt.uiktpteamretrobnd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @OneToMany
    private List<Category> categories;

    @OneToMany
    private List<Team> teams;

    @ManyToMany
    private List<User> users;

    public Retrospective() {
    }

    public Retrospective(String title, LocalDate date, String sprintName, RetrospectiveStatus status) {
        this.title = title;
        this.date = date;
        this.sprintName = sprintName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}