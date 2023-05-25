package uikt.uiktpteamretrobnd.web.requests;

public class CategoryRequest {

    private String name;
    private String description;

    private Long retrospectiveId;

    public CategoryRequest(String name, String description, Long retrospectiveId) {
        this.name = name;
        this.description = description;
        this.retrospectiveId = retrospectiveId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRetrospectiveId() {
        return retrospectiveId;
    }

    public void setRetrospectiveId(Long retrospectiveId) {
        this.retrospectiveId = retrospectiveId;
    }
}
