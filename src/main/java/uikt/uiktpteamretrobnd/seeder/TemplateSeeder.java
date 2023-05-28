package uikt.uiktpteamretrobnd.seeder;

import org.springframework.stereotype.Component;
import uikt.uiktpteamretrobnd.model.Template;
import uikt.uiktpteamretrobnd.repository.TemplateRepository;

@Component
public class TemplateSeeder {
    private final TemplateRepository templateRepository;

    public TemplateSeeder(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public void seed(){
        this.createAgileRetrospective();
        this.createAnchorsAndEngines();
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
                "    \"Explain what went well\",\n" +
                "    \"WentWell.png\"\n" +
                "  ],\n" +
                "    \"Category2\": [\n" +
                "    \"What went less well?\",\n" +
                "    \"Explain what went less well\",\n" +
                "    \"LessWell.png\"\n" +
                "  ],\n" +
                "    \"Category3\": [\n" +
                "    \"What do we want to try next?\",\n" +
                "    \"What do we want to try next\",\n" +
                "    \"TryNext.png\"\n" +
                "  ],\n" +
                "    \"Category4\": [\n" +
                "    \"What puzzles us?\",\n" +
                "    \"What puzzles us\",\n" +
                "    \"Puzzle.png\"\n" +
                "  ]\n" +
                "}";

        Template template = new Template(format, name, "AgileRetrospectiveImage.png");
        this.templateRepository.save(template);
    }

    private void createAnchorsAndEngines(){
        String name = "Anchors & Engines";
        String format = "{\n" +
                "  \"CategoriesCount\": 2,\n" +
                "  \"Category1\": [\n" +
                "    \"Anchors\",\n" +
                "    \"What were our anchors\",\n" +
                "    \"Anchors.png\"\n" +
                "  ],\n" +
                "    \"Category2\": [\n" +
                "    \"Engines\",\n" +
                "    \"What were our engines\",\n" +
                "    \"Engines.png\"\n" +
                "  ]\n" +
                "}";

        Template template = new Template(format, name, "AnchorsAndEngines.png");
        this.templateRepository.save(template);
    }

    private void createDropAddKeepImprove(){
        String name = "Drop Add Keep Improve";
        String format = "{\n" +
                "  \"CategoriesCount\": 4,\n" +
                "  \"Category1\": [\n" +
                "    \"Drop\",\n" +
                "    \"What should we drop\",\n" +
                "    \"Drop.png\"\n" +
                "  ],\n" +
                "    \"Category2\": [\n" +
                "    \"Add\",\n" +
                "    \"What should we add\",\n" +
                "    \"Add.png\"\n" +
                "  ],\n" +
                "    \"Category3\": [\n" +
                "    \"Keep\",\n" +
                "    \"What should we keep\",\n" +
                "    \"Keep.png\"\n" +
                "  ],\n" +
                "    \"Category4\": [\n" +
                "    \"Improve\",\n" +
                "    \"What should we improve\",\n" +
                "    \"Improve.png\"\n" +
                "  ]\n" +
                "}";

        Template template = new Template(format, name, "Daki.png");
        this.templateRepository.save(template);
    }

    private void createFourLs(){
        String name = "Four Ls";
        String format = "{\n" +
                "  \"CategoriesCount\": 4,\n" +
                "  \"Category1\": [\n" +
                "    \"Liked\",\n" +
                "    \"What did we liked\",\n" +
                "    \"Liked.png\"\n" +
                "  ],\n" +
                "    \"Category2\": [\n" +
                "    \"Learned\",\n" +
                "    \"What did we learned\",\n" +
                "    \"Learned.png\"\n" +
                "  ],\n" +
                "    \"Category3\": [\n" +
                "    \"Lacked\",\n" +
                "    \"What did we lacked\",\n" +
                "    \"Lacked.png\"\n" +
                "  ],\n" +
                "    \"Category4\": [\n" +
                "    \"Longed For\",\n" +
                "    \"What did we longed For\",\n" +
                "    \"LongedFor.png\"\n" +
                "  ]\n" +
                "}";

        Template template = new Template(format, name, "FourLs.png");
        this.templateRepository.save(template);
    }

    private void createMadSadGlad(){
        String name = "Mad Sad Glad";
        String format = "{\n" +
                "  \"CategoriesCount\": 3,\n" +
                "  \"Category1\": [\n" +
                "    \"Mad\",\n" +
                "    \"What are we mad about\",\n" +
                "    \"Mad.png\"\n" +
                "  ],\n" +
                "    \"Category2\": [\n" +
                "    \"Sad\",\n" +
                "    \"What are we sad about\",\n" +
                "    \"Sad.png\"\n" +
                "  ],\n" +
                "    \"Category3\": [\n" +
                "    \"Glad\",\n" +
                "    \"What are we glad about\",\n" +
                "    \"Glad.png\"\n" +
                "  ]\n" +
                "}";

        Template template = new Template(format, name, "MadSadGlad.png");
        this.templateRepository.save(template);
    }

    private void createStartStopContinue(){
        String name = "Start Stop Continue";
        String format = "{\n" +
                "  \"CategoriesCount\": 3,\n" +
                "  \"Category1\": [\n" +
                "    \"Start\",\n" +
                "    \"What should we start\",\n" +
                "    \"Start.png\"\n" +
                "  ],\n" +
                "    \"Category2\": [\n" +
                "    \"Stop\",\n" +
                "    \"What should we stop\",\n" +
                "    \"Stop.png\"\n" +
                "  ],\n" +
                "    \"Category3\": [\n" +
                "    \"Continue\",\n" +
                "    \"What should we continue\",\n" +
                "    \"Continue.png\"\n" +
                "  ]\n" +
                "}";

        Template template = new Template(format, name, "StartStopContinue.png");
        this.templateRepository.save(template);
    }
}
