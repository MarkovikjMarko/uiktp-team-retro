package uikt.uiktpteamretrobnd.web.requests;

public class InviteRequest {
    private Long retrospectiveId;
    private Long userId;

    private Long teamId;

    public InviteRequest(Long retrospectiveId, Long userId, Long teamId) {
        this.retrospectiveId = retrospectiveId;
        this.userId = userId;
        this.teamId = teamId;
    }

    public Long getRetrospectiveId() {
        return retrospectiveId;
    }

    public void setRetrospectiveId(Long retrospectiveId) {
        this.retrospectiveId = retrospectiveId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
