package com.example.recipeapi;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.ArrayList;

public interface RecipeRepository extends PagingAndSortingRepository<Recipe, String>, MongoRepository<Recipe, String> {
    
    ArrayList<Recipe> findByCategory(String category);

    ArrayList<Recipe> findByTitleContainingIgnoreCase(String title);

    ArrayList<Recipe> findByCookingTimeLessThanEqual(int cookingTime);
}
