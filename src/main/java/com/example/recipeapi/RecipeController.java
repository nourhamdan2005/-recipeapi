package com.example.recipeapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    // ✅ GET: List all recipes with pagination
    @GetMapping
    public Page<Recipe> getAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    // ✅ POST: Create a new recipe
    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    // ✅ GET: Get a recipe by ID
    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable String id) {
        return recipeRepository.findById(id).orElse(null);
    }

    // ✅ PUT: Update a recipe
    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable String id, @RequestBody Recipe updatedRecipe) {
        return recipeRepository.findById(id).map(recipe -> {
            recipe.setTitle(updatedRecipe.getTitle());
            recipe.setIngredients(updatedRecipe.getIngredients());
            recipe.setInstructions(updatedRecipe.getInstructions());
            recipe.setCookingTime(updatedRecipe.getCookingTime());
            recipe.setCategory(updatedRecipe.getCategory());
            return recipeRepository.save(recipe);
        }).orElse(null);
    }

    // ✅ DELETE: Delete a recipe
    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable String id) {
        recipeRepository.deleteById(id);
    }

    // ✅ GET: Search recipes by category
    @GetMapping("/searchByCategory")
    public ArrayList<Recipe> searchByCategory(@RequestParam String category) {
        return recipeRepository.findByCategory(category);
    }

    // ✅ GET: Search recipes by title
    @GetMapping("/searchByTitle")
    public ArrayList<Recipe> searchByTitle(@RequestParam String title) {
        return recipeRepository.findByTitleContainingIgnoreCase(title);
    }

    // ✅ GET: Search recipes by cooking time
    @GetMapping("/searchByCookingTime")
    public ArrayList<Recipe> searchByCookingTime(@RequestParam int time) {
        return recipeRepository.findByCookingTimeLessThanEqual(time);
    }

}
