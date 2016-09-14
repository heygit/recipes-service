package project.model;

import java.util.List;

/**
 * Created by Green-L on 13.09.2016.
 */
public class Formula {

    private String time;
    private String title;
    private int calories;
    private int portions;
    private String category;
    private int rating;
    private List<Ingredient> ingredients;
    private List<String> steps;

    public Formula(String time, int calories, int portions, String category, int rating,
                   List<Ingredient> ingredients, List<String> steps, String title) {
        this.time = time;
        this.calories = calories;
        this.portions = portions;
        this.category = category;
        this.rating = rating;
        this.ingredients = ingredients;
        this.steps = steps;
        this.title = title;
    }

    public Formula() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Formula{" +
                "time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", calories=" + calories +
                ", portions=" + portions +
                ", category='" + category + '\'' +
                ", rating=" + rating +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                '}';
    }
}
