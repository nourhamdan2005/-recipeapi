package com.example.recipeapi;
import org.springframework.data.annotation.Id;//Tells MongoDB: "This is the special field that will be the _id in the database."
import org.springframework.data.mongodb.core.mapping.Document;//Tells Spring Boot that this Java class (Recipe) will be stored as a document inside a MongoDB collection.
import java.util.ArrayList;
@Document(collection = "recipes")
public class Recipe {
	  @Id
	    private String id;
	    private String title;
	    private ArrayList<String> ingredients;
	    private String instructions;
	    private int cookingTime;
	    private String category;
	    //needed by Spring
		public Recipe() {}
//except id will be auto-generated
		public Recipe(String title, ArrayList<String> ingredients, String instructions, int cookingTime,
				String category) {
			super();
			this.title = title;
			this.ingredients = ingredients;
			this.instructions = instructions;
			this.cookingTime = cookingTime;
			this.category = category;
		}
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public ArrayList<String> getIngredients() {
			return ingredients;
		}
		public void setIngredients(ArrayList<String> ingredients) {
			this.ingredients = ingredients;
		}
		public String getInstructions() {
			return instructions;
		}
		public void setInstructions(String instructions) {
			this.instructions = instructions;
		}
		public int getCookingTime() {
			return cookingTime;
		}
		public void setCookingTime(int cookingTime) {
			this.cookingTime = cookingTime;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
	    
	    
	    
}
