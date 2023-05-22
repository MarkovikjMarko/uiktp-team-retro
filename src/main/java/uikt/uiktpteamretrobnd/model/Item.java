package uikt.uiktpteamretrobnd.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @Lazy
    private Category category;

    public Item() {

    }

    public Item(String body, Category category) {
        this.body = body;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
