package uikt.uiktpteamretrobnd.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uikt.uiktpteamretrobnd.enums.RetrospectiveStatus;
import uikt.uiktpteamretrobnd.model.*;
import uikt.uiktpteamretrobnd.model.exceptions.ModelNotFoundException;
import uikt.uiktpteamretrobnd.repository.*;
import uikt.uiktpteamretrobnd.web.requests.RetrospectiveRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RetrospectiveService {
    private final RetrospectiveRepository retrospectiveRepository;

    private final UserRepository userRepository;
    private final TemplateRepository templateRepository;
    private final CategoryRepository categoryRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public RetrospectiveService(RetrospectiveRepository retrospectiveRepository, UserRepository userRepository, TemplateRepository templateRepository, CategoryRepository categoryRepository, TeamRepository teamRepository) {
        this.retrospectiveRepository = retrospectiveRepository;
        this.userRepository = userRepository;
        this.templateRepository = templateRepository;
        this.categoryRepository = categoryRepository;
        this.teamRepository = teamRepository;
    }

    public List<Retrospective> findAll() {
        return retrospectiveRepository.findAll();
    }

    public Optional<Retrospective> find(Long id) {
        return retrospectiveRepository.findById(id);
    }

    public Retrospective create(RetrospectiveRequest retrospectiveRequest) {
        String title = retrospectiveRequest.getTitle();
        LocalDate date = retrospectiveRequest.getDate();
        String sprintName = retrospectiveRequest.getSprintName();
        RetrospectiveStatus status = retrospectiveRequest.getStatus();
        Long templateId = retrospectiveRequest.getTemplateId();
        Long teamId = retrospectiveRequest.getTeamId();

        User creator = this.userRepository.findById(retrospectiveRequest.getCreatorId()).orElseThrow(ModelNotFoundException::new);

        Team team = this.teamRepository.findById(teamId).get();

        Retrospective retrospective = new Retrospective(title, date, sprintName, status, creator, team);

        retrospectiveRepository.save(retrospective);

        if(templateId != null){
            retrospective = this.createRetrospectiveFromTemplate(retrospective, templateId);
        }

        return retrospective;
    }
    public Retrospective createRetrospectiveFromTemplate(Retrospective retrospective, Long templateId){
        Template template = this.templateRepository.findById(templateId).orElseThrow(ModelNotFoundException::new);

        JSONObject json = new JSONObject(template.getFormat());

        int categoriesCount = json.getInt("CategoriesCount");

        for(int i = 1; i <= categoriesCount; i++){
            JSONArray categoryArray = json.getJSONArray("Category" + i);
            String name = categoryArray.getString(0);
            String description = categoryArray.getString(1);
            String imageName = categoryArray.getString(2);

            Category category = new Category(name, description, retrospective, imageName);
            this.categoryRepository.save(category);
        }

        return retrospective;
    }

    public Retrospective update(Long id, RetrospectiveRequest retrospectiveRequest) {
        Retrospective retrospective = this.retrospectiveRepository.findById(id).orElseThrow(ModelNotFoundException::new);

        String title = retrospectiveRequest.getTitle();
        LocalDate date = retrospectiveRequest.getDate();
        String sprintName = retrospectiveRequest.getSprintName();
        RetrospectiveStatus status = retrospectiveRequest.getStatus();
        Long creatorId = retrospectiveRequest.getCreatorId();
        Long teamId = retrospectiveRequest.getTeamId();

        if(title != null){
            retrospective.setTitle(title);
        }

        if(date != null){
            retrospective.setDate(date);
        }

        if(sprintName != null){
            retrospective.setSprintName(sprintName);
        }

        if(status != null){
            retrospective.setStatus(status);
        }

        if(creatorId != null){
            User creator = this.userRepository.findById(retrospectiveRequest.getCreatorId()).orElseThrow(ModelNotFoundException::new);
            retrospective.setCreator(creator);
        }

        if(teamId != null){
            Team team = this.teamRepository.findById(teamId).get();
            retrospective.setTeam(team);
        }

        retrospectiveRepository.save(retrospective);

        return retrospective;
    }

    public boolean delete(Long id) {
        if (retrospectiveRepository.existsById(id)) {
            retrospectiveRepository.deleteById(id);

            return true;
        }

        return false;
    }
}