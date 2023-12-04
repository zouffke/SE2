package be.kdg.hifresh.domain.recepten;

import be.kdg.hifresh.domain.aankoop.Product;

import java.util.ArrayList;
import java.util.List;

public class IngredientCataloog {

    private final List<Ingredient> ingredienten;

    IngredientCataloog() {
        this.ingredienten = new ArrayList<>();
    }

    Ingredient getIngredient(int id) {
        return this.ingredienten.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    Ingredient addIngredient(Ingredient ingredient) {
        if (this.getIngredient(ingredient.getId()) != null) {
            return getIngredient(ingredient.getId());
        }

        this.ingredienten.add(ingredient);
        return ingredient;
    }

    Ingredient createNewIngredient(int id, Product product, double hoeveelheid) {
        return new Ingredient(id, product, hoeveelheid);
    }
}