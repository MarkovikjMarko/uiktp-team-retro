package uikt.uiktpteamretrobnd.web.requests;

public class TeamRequest {
    private String name;

    private Long leaderId;

    public TeamRequest(String name, Long leaderId) {
        this.name = name;
        this.leaderId = leaderId;
    }

    public TeamRequest() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }
}
