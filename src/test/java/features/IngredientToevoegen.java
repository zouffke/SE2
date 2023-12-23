package features;

import be.kdg.hifresh.applicationLayer.Controller;
import be.kdg.hifresh.applicationLayer.aankoop.AankoopController;
import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import be.kdg.hifresh.businessLayer.domain.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.domain.recepten.Recept;
import be.kdg.hifresh.businessLayer.domain.util.Eenheid;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IngredientToevoegen {
    @When("ik product met id {int} toevoeg aan recept met id {int} met een hoeveelheid van {double} en eenheid {string} op stap {int}")
    public void ikProductMetIdToevoegAanReceptMetIdMetEenHoeveelheidVanEnEenheidOpStap(int arg0, int arg1, double arg2, String arg3, int arg4) {
        try {
            Product product = AankoopController.getProduct(arg0);
            assertTrue(Controller.addIngredient(
                    10,
                    product.getNAME(),
                    arg0,
                    arg2,
                    Eenheid.valueOf(arg3.toUpperCase())
            ));
            List<Integer> ingr = new ArrayList<>();
            ingr.add(10);
            Controller.addIngredientToBereidingstap(
                    arg1,
                    arg4,
                    ingr
            );
        } catch (InvocationTargetException | IllegalAccessException e) {
            Assertions.fail(e);
        }
    }

    @Then("heeft het recept {int}, {int} ingredienten")
    public void heeftHetReceptIngredienten(int arg0, int arg1) {
        try {
            assertEquals(arg1, Controller.getRecept(arg0).getIngredients().size());
        } catch (InvocationTargetException | IllegalAccessException e) {
            Assertions.fail(e);
        }
    }

    @And("een van de ingredienten van recept {int} is product {int} met hoeveelheid {double} {string}")
    public void eenVanDeIngredientenVanReceptIsProductMetHoeveelheid(int arg0, int arg1, double arg2, String arg3) {
        try {
            List<Ingredient> ingredients = Controller.getRecept(arg0).getIngredients();

            for (Ingredient ingredient : ingredients) {
                if (ingredient.getPRODUCT().getID() == arg1) {
                    assertEquals(Eenheid.valueOf(arg3.toUpperCase()), ingredient.getEENHEID());
                    assertEquals(arg2, ingredient.getHOEVEELHEID());
                    return;
                }
            }
            Assertions.fail();

        } catch (InvocationTargetException | IllegalAccessException e) {
            Assertions.fail(e);
        }
    }
}
