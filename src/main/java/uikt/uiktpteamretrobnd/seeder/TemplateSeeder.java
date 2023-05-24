package uikt.uiktpteamretrobnd.seeder;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import uikt.uiktpteamretrobnd.model.Template;
import uikt.uiktpteamretrobnd.repository.TemplateRepository;

@Component
public class TemplateSeeder {
    private final TemplateRepository templateRepository;


    public TemplateSeeder(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @PostConstruct
    public void seed(){
        this.createAgileRetrospective();
        this.createAnchorsAndEngines();
        this.createDropAddKeepImprove();
        this.createDropAddKeepImprove();
        this.createFourLs();
        this.createMadSadGlad();
        this.createStartStopContinue();
    }

    private void createAgileRetrospective(){
        String name = "Agile Retrospective";
        String format = "{\n" +
                "  \"CategoriesCount\": 4,\n" +
                "  \"Category1\": [\n" +
                "    \"What went well?\",\n" +
                "    \"Explain what went well\"\n" +
                "  ],\n" +
                "    \"Category2\": [\n" +
                "    \"What went less well?\",\n" +
                "    \"Explain what went less well\"\n" +
                "  ],\n" +
                "    \"Category3\": [\n" +
                "    \"What do we want to try next?\",\n" +
                "    \"What do we want to try next\"\n" +
                "  ],\n" +
                "    \"Category4\": [\n" +
                "    \"What puzzles us?\",\n" +
                "    \"What puzzles us\"\n" +
                "  ]\n" +
                "}";

        Template template = new Template(format, name);
        this.templateRepository.save(template);
    }

    private void createAnchorsAndEngines(){
        String name = "Anchors & Engines";
        String format = "{\n" +
                "  \"CategoriesCount\": 2,\n" +
                "  \"Category1\": [\n" +
                "    \"Anchors\",\n" +
                "    \"What were our anchors\"\n" +
                "  ],\n" +
                "    \"Category2\": [\n" +
                "    \"Engines\",\n" +
                "    \"What were our engines\"\n" +
                "  ]\n" +
                "}";

        Template template = new Template(format, name);
        this.templateRepository.save(template);
    }

    private void createDropAddKeepImprove(){
        String name = "Drop Add Keep Improve";
        String format = "{\n" +
                "  \"CategoriesCount\": 4,\n" +
                "  \"Category1\": [\n" +
                "    \"Drop\",\n" +
                "    \"What should we drop\"\n" +
                "  ],\n" +
                "    \"Category2\": [\n" +
                "    \"Add\",\n" +
                "    \"What should we add\"\n" +
                "  ],\n" +
                "    \"Category3\": [\n" +
                "    \"Keep\",\n" +
                "    \"What should we keep\"\n" +
                "  ],\n" +
                "    \"Category4\": [\n" +
                "    \"Improve\",\n" +
                "    \"What should we improve\"\n" +
                "  ]\n" +
                "}";

        Template template = new Template(format, name);
        this.templateRepository.save(template);
    }

    private void createFourLs(){
        String name = "Four Ls";
        String format = "{\n" +
                "  \"CategoriesCount\": 4,\n" +
                "  \"Category1\": [\n" +
                "    \"Liked\",\n" +
                "    \"What did we liked\"\n" +
                "  ],\n" +
                "    \"Category2\": [\n" +
                "    \"Learned\",\n" +
                "    \"What did we learned\"\n" +
                "  ],\n" +
                "    \"Category3\": [\n" +
                "    \"Lacked\",\n" +
                "    \"What did we lacked\"\n" +
                "  ],\n" +
                "    \"Category4\": [\n" +
                "    \"Longed For\",\n" +
                "    \"What did we longed For\"\n" +
                "  ]\n" +
                "}";

        Template template = new Template(format, name);
        this.templateRepository.save(template);
    }

    private void createMadSadGlad(){
        String name = "Mad Sad Glad";
        String format = "{\n" +
                "  \"CategoriesCount\": 3,\n" +
                "  \"Category1\": [\n" +
                "    \"Mad\",\n" +
                "    \"What are we mad about\"\n" +
                "  ],\n" +
                "    \"Category2\": [\n" +
                "    \"Sad\",\n" +
                "    \"What are we sad about\"\n" +
                "  ],\n" +
                "    \"Category3\": [\n" +
                "    \"Glad\",\n" +
                "    \"What are we glad about\"\n" +
                "  ]\n" +
                "}";

        Template template = new Template(format, name);
        this.templateRepository.save(template);
    }

    private void createStartStopContinue(){
        String name = "Start Stop Continue";
        String format = "{\n" +
                "  \"CategoriesCount\": 3,\n" +
                "  \"Category1\": [\n" +
                "    \"Start\",\n" +
                "    \"What should we start\"\n" +
                "  ],\n" +
                "    \"Category2\": [\n" +
                "    \"Stop\",\n" +
                "    \"What should we stop\"\n" +
                "  ],\n" +
                "    \"Category3\": [\n" +
                "    \"Continue\",\n" +
                "    \"What should we continue\"\n" +
                "  ]\n" +
                "}";

        Template template = new Template(format, name);
        this.templateRepository.save(template);
    }
}
