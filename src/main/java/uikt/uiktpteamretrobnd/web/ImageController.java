package uikt.uiktpteamretrobnd.web;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class ImageController {

    @GetMapping("/images/{fileName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) throws IOException {
        String path = "src/main/images/";

        if (this.checkIfItsTemplateImage(fileName)) {
            path += "templateImages/";
        } else if (this.checkIfItsCategoryImage(fileName)) {
            path += "categoryImages/";
        } else {
            path += "uploadedImages/";
        }

        Resource resource = new FileSystemResource(path + fileName);

        if (resource.exists()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Boolean checkIfItsTemplateImage(String fileName) {
        List<String> templateImagesNames = Arrays.asList(
                "AgileRetrospectiveImage.png",
                "AnchorsAndEngines.png",
                "Daki.png",
                "FourLs.png",
                "MadSadGlad.png",
                "StartStopContinue.png",
                "Unknown_person.jpg" // this is a user image
        );

        return templateImagesNames.contains(fileName);
    }

    public Boolean checkIfItsCategoryImage(String fileName) {
        List<String> categoryImagesNames = Arrays.asList(
                "Add.png",
                "Anchors.png",
                "Continue.png",
                "Drop.png",
                "Engines.png",
                "Glad.png",
                "Improve.png",
                "Keep.png",
                "Lacked.png",
                "Learned.png",
                "LessWell.png",
                "Liked.png",
                "LongedFor.png",
                "Mad.png",
                "Puzzle.png",
                "Sad.png",
                "Start.png",
                "Stop.png",
                "TryNext.png",
                "WentWell.png"
        );

        return categoryImagesNames.contains(fileName);
    }
}
