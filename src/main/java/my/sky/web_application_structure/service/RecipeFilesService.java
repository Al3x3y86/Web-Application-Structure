package my.sky.web_application_structure.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class RecipeFilesService {
    @Value("${path.to.recipes.file}")
    private String recipesFilePath;

    @Value("${name.of.recipes.file}")
    private String recipeFileName;

    public boolean saveRecipesToFile (String json) {
        try {
            cleanRecipeFile();
            Files.writeString(Path.of(recipesFilePath, recipeFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String readRecipesFromFile(){
        if (Files.exists(Path.of(recipesFilePath, recipeFileName))) {
            try {
                return Files.readString(Path.of(recipesFilePath, recipeFileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "";
    }

    public void cleanRecipeFile(){
        try {
            Path path = Path.of(recipesFilePath, recipeFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        if (Files.exists(Path.of(recipesFilePath, recipeFileName))) {
            return new File(recipesFilePath + "/" + recipeFileName);
        }
        return null;
    }
}