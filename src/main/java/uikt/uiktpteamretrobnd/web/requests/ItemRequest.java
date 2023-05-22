package uikt.uiktpteamretrobnd.web.requests;

public class ItemRequest {
    private String body;
    private Long categoryId;

    public ItemRequest(String body, Long categoryId) {
        this.body = body;
        this.categoryId = categoryId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
