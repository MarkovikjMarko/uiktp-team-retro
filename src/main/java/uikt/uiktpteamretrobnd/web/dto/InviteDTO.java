package uikt.uiktpteamretrobnd.web.dto;

import uikt.uiktpteamretrobnd.model.Invite;

public class InviteDTO {
    private Long id;
    private Long retrospectiveId;
    private Long userId;

    public InviteDTO(Invite invite) {
        this.id = invite.getId();
        this.retrospectiveId = invite.getRetrospective().getId();
        this.userId = invite.getUser().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
