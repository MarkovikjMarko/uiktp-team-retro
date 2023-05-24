package uikt.uiktpteamretrobnd.web.requests;

import org.springframework.web.multipart.MultipartFile;

public class UserRequest {
    private String name;

    private String email;

    private String password;

    private MultipartFile image;

    public UserRequest(String name, String email, String password, MultipartFile image) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
