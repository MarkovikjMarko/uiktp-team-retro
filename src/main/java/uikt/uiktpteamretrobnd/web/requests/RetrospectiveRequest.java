package uikt.uiktpteamretrobnd.web.requests;

import uikt.uiktpteamretrobnd.enums.RetrospectiveStatus;

import java.time.LocalDate;

public class RetrospectiveRequest {
    private String title;

    private LocalDate date;

    private String sprintName;

    private RetrospectiveStatus status;

    private Long creatorId;

    private Long templateId;

    public RetrospectiveRequest(String title, LocalDate date, String sprintName, RetrospectiveStatus status, Long creatorId, Long templateId) {
        this.title = title;
        this.date = date;
        this.sprintName = sprintName;
        this.status = status;
        this.creatorId = creatorId;
        this.templateId = templateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public RetrospectiveStatus getStatus() {
        return status;
    }

    public void setStatus(RetrospectiveStatus status) {
        this.status = status;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
}