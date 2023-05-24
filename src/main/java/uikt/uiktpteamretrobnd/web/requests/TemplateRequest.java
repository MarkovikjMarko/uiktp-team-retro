package uikt.uiktpteamretrobnd.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemplateRequest {


    @JsonProperty("format")
    private String format;

    public TemplateRequest(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}