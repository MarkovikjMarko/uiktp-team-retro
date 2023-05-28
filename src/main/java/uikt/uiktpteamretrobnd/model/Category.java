package uikt.uiktpteamretrobnd.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String imageName;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "retrospective_id")
    private Retrospective retrospective;

    public Category() {
    }

    public Category(String name, String description, Retrospective retrospective, String imageName) {
        this.name = name;
        this.description = description;
        this.retrospective = retrospective;
        this.imageName = imageName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRetrospective(Retrospective retrospective) {
        this.retrospective = retrospective;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
