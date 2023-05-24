package uikt.uiktpteamretrobnd.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemplateRequest {


    @JsonProperty("format")
    private String format;

    private String name;

    public TemplateRequest(String format, String name) {
        this.format = format;
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}