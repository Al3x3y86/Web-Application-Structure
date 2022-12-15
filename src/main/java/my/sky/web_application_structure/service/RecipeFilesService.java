package my.sky.web_application_structure.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class RecipeFilesService {
    @Value("${path.to.recipesJson.file}")
    private String recipesFilePath;

    @Value("${name.of.recipesJson.file}")
    private String recipeFileName;

    @Value("${path.to.recipesTXT.file}")
    private String recipesTxtFilePath;

    @Value("${name.of.recipesTXT.file}")
    private String recipeTxtFileName;

    public boolean saveRecipesToJsonFile(String json) {
        try {
            cleanRecipeJsonFile();
            Files.writeString(Path.of(recipesFilePath, recipeFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public boolean saveRecipesToTxtFile(String txt) {
        try {
            cleanRecipeTxtFile();
            Files.writeString(Path.of(recipesTxtFilePath, recipeTxtFileName), txt);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String readRecipesFromJsonFile(){
        if (Files.exists(Path.of(recipesFilePath, recipeFileName))) {
            try {
                return Files.readString(Path.of(recipesFilePath, recipeFileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "";
    }

    public void cleanRecipeJsonFile(){
        try {
            Path path = Path.of(recipesFilePath, recipeFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cleanRecipeTxtFile(){
        try {
            Path path = Path.of(recipesTxtFilePath, recipeTxtFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getJsonFile() {
        if (Files.exists(Path.of(recipesFilePath, recipeFileName))) {
            return new File(recipesFilePath + "/" + recipeFileName);
        }
        return null;
    }

    public File getTxtFile() {
        if (Files.exists(Path.of(recipesTxtFilePath, recipeTxtFileName))) {
            return new File(recipesTxtFilePath + "/" + recipeTxtFileName);
        }
        return null;
    }
}