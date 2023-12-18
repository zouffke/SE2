package features;

import be.kdg.hifresh.applicationLayer.Controller;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubreceptToevoegen {
    @When("ik het recept {int} toevoeg aan recept met id {int} na stap {int}")
    public void ikHetReceptToevoegAanReceptMetIdNaStap(int arg0, int arg1, int arg2) {
        try {
            assertTrue(Controller.addSubreceptToRecept(arg0, arg1, arg2));
        } catch (InvocationTargetException | IllegalAccessException e) {
            Assertions.fail(e);
        }
    }


    @Then("heeft recept {int} een subrecept met id {int}")
    public void heeftReceptEenSubreceptMetId(int arg0, int arg1) {
        try {
            assertEquals(arg1, Controller.getRecept(arg0).getSubrecepten().get(0).getId());
        } catch (InvocationTargetException | IllegalAccessException e) {
            Assertions.fail(e);
        }
    }


    @And("recept {int} is een subrecept van recept {int}")
    public void receptIsEenSubreceptVanRecept(int arg0, int arg1) {
        try {
            assertEquals(arg1, Controller.getRecept(arg0).getSuperRecepten().get(0).getId());
        } catch (InvocationTargetException | IllegalAccessException e) {
            Assertions.fail(e);
        }
    }
}
