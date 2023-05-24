package uikt.uiktpteamretrobnd.web.requests;

public class ItemRequest {
    private String body;
    private Long categoryId;

    private Long userId;

    public ItemRequest(String body, Long categoryId, Long userId) {
        this.body = body;
        this.categoryId = categoryId;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
