package my.sky.web_application_structure.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class IngredientsFilesService {

    @Value("${path.to.ingredients.file}")
    private String ingredientsFilePath;

    @Value("${name.of.ingredients.file}")
    private String ingredientsFileName;

    public boolean saveIngredientsToFile (String json) {
        try {
            cleanIngredientsFile();
            Files.writeString(Path.of(ingredientsFilePath, ingredientsFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String readIngredientsFromFile(){
        if (Files.exists(Path.of(ingredientsFilePath, ingredientsFileName))) {
            try {
                return Files.readString(Path.of(ingredientsFilePath, ingredientsFileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void cleanIngredientsFile(){
        try {
            Path path = Path.of(ingredientsFilePath, ingredientsFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public File getFile() {
        if (Files.exists(Path.of(ingredientsFilePath, ingredientsFileName))) {
            return new File(ingredientsFilePath + "/" + ingredientsFileName);
        }
        return null;
    }
}
