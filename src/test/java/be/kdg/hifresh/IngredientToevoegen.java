package be.kdg.hifresh;

import be.kdg.hifresh.applicationLayer.recepten.ReceptController;
import be.kdg.hifresh.businessLayer.domain.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.domain.util.Eenheid;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class IngredientToevoegen {

    //region vars

    @Autowired
    ReceptController receptController;

    //endregion

    @When("ik product met id {int} toevoeg aan recept met id {int} met een hoeveelheid van {double} en eenheid {string} op stap {int}")
    public void ikProductMetIdToevoegAanReceptMetIdMetEenHoeveelheidVanEnEenheidOpStap(int arg0, int arg1, double arg2, String arg3, int arg4) {
        assertTrue(receptController.addIngredient(
                10,
                arg0,
                arg2,
                Eenheid.valueOf(arg3.toUpperCase())
        ));
        List<Integer> ingr = new ArrayList<>();
        ingr.add(10);
        receptController.addIngredientToBereidingstap(
                arg1,
                arg4,
                ingr
        );
    }

    @Then("heeft het recept {int}, {int} ingredienten")
    public void heeftHetReceptIngredienten(int arg0, int arg1) {
        assertEquals(arg1, receptController.getAllIngredients(arg0).size());
    }

    @And("een van de ingredienten van recept {int} is product {int} met hoeveelheid {double} {string}")
    public void eenVanDeIngredientenVanReceptIsProductMetHoeveelheid(int arg0, int arg1, double arg2, String arg3) {
        List<Ingredient> ingredients = receptController.getAllIngredients(arg0);

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getPRODUCT().getID() == arg1) {
                assertEquals(Eenheid.valueOf(arg3.toUpperCase()), ingredient.getEENHEID());
                assertEquals(arg2, ingredient.getHOEVEELHEID());
                return;
            }
        }
        Assertions.fail();
    }
}
