package be.kdg.hifresh.domain.recepten;

import be.kdg.hifresh.domain.aankoop.Product;

public class ReceptenFactory {

    private static IngredientCataloog ingredientCataloog;
    private static ReceptCataloog receptCataloog;

    private ReceptenFactory() {
    }

    static BereidingsStap createBereidingsStap(int id, String naam, String beschrijving) {
        return new BereidingsStap(naam, beschrijving, id);
    }

    static Ingredient createIngredient(int id, Product product, double hoeveelheid){
        return new Ingredient(id, product, hoeveelheid);
    }

    static IngredientCataloog createIngredientCataloog(){
        if (ingredientCataloog == null){
            ingredientCataloog = new IngredientCataloog();
        }

        return ingredientCataloog;
    }

    static Recept createRecept(int id, String naam, String beschrijving){
        return new Recept(naam, id, beschrijving);
    }

    static ReceptCataloog createReceptCataloog(){
        if (receptCataloog == null){
            receptCataloog = new ReceptCataloog();
        }

        return receptCataloog;
    }

    static ReceptManager createReceptManager(){
        return new ReceptManager();
    }
}
