package uikt.uiktpteamretrobnd.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public class TemplateRequest {


    @JsonProperty("format")
    private String format;

    private String name;

    private MultipartFile image;

    public TemplateRequest(String format, String name, MultipartFile image) {
        this.format = format;
        this.name = name;
        this.image = image;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}