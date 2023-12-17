package features;

import be.kdg.hifresh.applicationLayer.Controller;
import be.kdg.hifresh.businessLayer.aankoop.Product;
import be.kdg.hifresh.businessLayer.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.recepten.Recept;
import be.kdg.hifresh.businessLayer.util.Eenheid;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IngredientToevoegen {
    @When("ik product met id {int} toevoeg aan recept met id {int} met een hoeveelheid van {double} en eenheid {string}")
    public void ikProductMetIdToevoegAanReceptMetIdMetEenHoeveelheidVanEnEenheid(int arg0, int arg1, double arg2, String arg3) {
        try {
            assertTrue(Controller.addIngredientToRecept(
                    7,
                    arg0,
                    arg1,
                    arg2,
                    Eenheid.valueOf(arg3.toUpperCase())
            ));
        } catch (InvocationTargetException | IllegalAccessException e){
            Assertions.fail(e);
        }
    }

    @Then("heeft het recept {int}, {int} ingredienten")
    public void heeftHetReceptIngredienten(int arg0, int arg1) {
        try{
            assertEquals(arg1, Controller.getRecept(arg0).getIngredienten().size());
        } catch (InvocationTargetException | IllegalAccessException e) {
            Assertions.fail(e);
        }
    }

    @And("een van de ingredienten van recept {int} is product {int} met hoeveelheid {double} {string}")
    public void eenVanDeIngredientenVanReceptIsProductMetHoeveelheid(int arg0, int arg1, double arg2, String arg3) {
        try{
            List<Ingredient> ingredients = Controller.getRecept(arg0).getIngredienten();

            for (Ingredient ingredient : ingredients){
                if (ingredient.getProduct().getId() == 2){
                    assertEquals(Eenheid.valueOf(arg3.toUpperCase()), ingredient.getEenheid());
                    assertEquals(arg2, ingredient.getHoeveelheid());
                    return;
                }
            }
            Assertions.fail();

        } catch (InvocationTargetException | IllegalAccessException e){
            Assertions.fail(e);
        }
    }
}
