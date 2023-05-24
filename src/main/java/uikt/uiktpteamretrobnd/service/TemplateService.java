package uikt.uiktpteamretrobnd.service;

import org.springframework.stereotype.Service;
import uikt.uiktpteamretrobnd.model.Template;
import uikt.uiktpteamretrobnd.model.exceptions.ModelNotFoundException;
import uikt.uiktpteamretrobnd.repository.TemplateRepository;
import uikt.uiktpteamretrobnd.web.requests.TemplateRequest;

import java.util.List;
import java.util.Optional;

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

    public Template create(TemplateRequest templateRequest){
        String format = templateRequest.getFormat();

        Template template = new Template(format);
        templateRepository.save(template);

        return template;
    }

    public Template update (Long id, TemplateRequest templateRequest){
        Template template = this.templateRepository.findById(id).orElseThrow(ModelNotFoundException::new);

        String format = templateRequest.getFormat();

        if (format != null){
            template.setFormat(format);
        }

        templateRepository.save(template);

        return template;
    }

    public boolean delete (Long id){
        if (templateRepository.existsById(id)){
            templateRepository.deleteById(id);

            return true;
        }
        return false;
    }


}
