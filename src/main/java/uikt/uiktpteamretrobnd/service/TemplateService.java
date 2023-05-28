package uikt.uiktpteamretrobnd.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uikt.uiktpteamretrobnd.model.Template;
import uikt.uiktpteamretrobnd.model.exceptions.ModelNotFoundException;
import uikt.uiktpteamretrobnd.repository.TemplateRepository;
import uikt.uiktpteamretrobnd.web.requests.TemplateRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public List<Template> findAll(){
        return templateRepository.findAll();
    }

    public Optional<Template> find(Long id){
        return templateRepository.findById(id);
    }

    public Template create(TemplateRequest templateRequest) throws IOException {
        String format = templateRequest.getFormat();
        String name = templateRequest.getName();
        MultipartFile image = templateRequest.getImage();

        String imageName = this.uploadImage(image);

        Template template = new Template(format, name, imageName);

        templateRepository.save(template);

        return template;
    }

    public Template update (Long id, TemplateRequest templateRequest) throws IOException {
        Template template = this.templateRepository.findById(id).orElseThrow(ModelNotFoundException::new);

        String format = templateRequest.getFormat();
        String name = templateRequest.getName();
        MultipartFile image = templateRequest.getImage();

        if (format != null){
            template.setFormat(format);
        }

        if(name != null){
            template.setName(name);
        }

        if (image != null) {
            String imageName = this.uploadImage(image);
            template.setImageName(imageName);
        }

        templateRepository.save(template);

        return template;
    }

    public String uploadImage(MultipartFile image) throws IOException {
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Image file is required");
        }

        String imageName = UUID.randomUUID().toString() + StringUtils.cleanPath(image.getOriginalFilename());

        String uploadDir = "src/main/images/uploadedImages/";

        // Create the directory if it doesn't exist
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Path filePath = Path.of(uploadDir + imageName);
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return imageName;
    }

    public boolean delete (Long id){
        if (templateRepository.existsById(id)){
            templateRepository.deleteById(id);

            return true;
        }
        return false;
    }
}
