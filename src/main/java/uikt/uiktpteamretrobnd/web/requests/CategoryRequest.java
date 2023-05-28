package uikt.uiktpteamretrobnd.web.requests;

import org.springframework.web.multipart.MultipartFile;

public class CategoryRequest {

    private String name;
    private String description;

    private Long retrospectiveId;

    private MultipartFile image;

    public CategoryRequest(String name, String description, Long retrospectiveId, MultipartFile image) {
        this.name = name;
        this.description = description;
        this.retrospectiveId = retrospectiveId;
        this.image = image;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
