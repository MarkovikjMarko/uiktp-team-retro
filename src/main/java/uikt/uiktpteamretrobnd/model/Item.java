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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Lazy
    private User user;

    public Item() {

    }

    public Item(String body, Category category, User user) {
        this.body = body;
        this.category = category;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
