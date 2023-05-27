package uikt.uiktpteamretrobnd.web;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uikt.uiktpteamretrobnd.model.Template;
import uikt.uiktpteamretrobnd.service.TemplateService;
import uikt.uiktpteamretrobnd.web.requests.TemplateRequest;
import uikt.uiktpteamretrobnd.web.response.ApiResponse;
import uikt.uiktpteamretrobnd.web.response.CustomResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/templates")
public class TemplateController extends ResponseEntityExceptionHandler {

    private final TemplateService templateService;

    private final CustomResponse response;

    @Autowired
    public TemplateController(TemplateService templateService, CustomResponse response) {
        this.templateService = templateService;
        this.response = response;
    }

    @GetMapping
    public ApiResponse<List<Template>> index(){
        List<Template> templates = templateService.findAll();

        return this.response.success(templates);
    }

    @GetMapping("/{id}")
    public ApiResponse<Template> show(@PathVariable("id") Long id){
        Optional<Template> template = templateService.find(id);

        if (template.isPresent()){
            return this.response.success(template);
        } else {
            return this.response.notFound(template);
        }
    }

    @PostMapping
    public ApiResponse<Template> create(@ModelAttribute @Valid TemplateRequest templateRequest) throws IOException {
        Template template = templateService.create(templateRequest);

        return this.response.created(template);
    }

    @PostMapping("/{id}")
    public ApiResponse<Template> update(@PathVariable("id") Long id, @ModelAttribute TemplateRequest templateRequest) throws IOException {
        Template template = templateService.update(id, templateRequest);

        return this.response.success(template);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") Long id){
        boolean deleted = templateService.delete(id);

        if (deleted){
            return this.response.deleted();
        } else{
            return this.response.notFound(null);
        }
    }

}
