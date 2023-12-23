package features;

import be.kdg.hifresh.applicationLayer.Controller;
import be.kdg.hifresh.businessLayer.domain.recepten.Recept;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@CucumberContextConfiguration
public class BereidingsStapToevoegen {

    @When("ik een bereidingsstap {string} {string} toevoeg aan recept met id {int}")
    public void ikEenBereidingsstapToevoegAanReceptMetId(String arg0, String arg1, int arg2) {
        Controller.addBereidingsStapToRecept(
                arg2,
                6,
                arg0,
                arg1
        );
    }

    @Then("heeft het recept {int}, {int} bereidingsstappen")
    public void heeftHetReceptBereidingsstappen(int arg0, int arg1) {
        try {
            assertEquals(arg1, Controller.getRecept(arg0).getStappen().size());
        } catch (InvocationTargetException | IllegalAccessException e) {
            Assertions.fail(e);
        }
    }

    @And("de laatste bereidingstap voor recept {int} heeft beschrijving {string}")
    public void deLaatsteBereidingstapVoorReceptHeeftBeschrijving(int arg0, String arg1) {
        try {
            Recept recept = Controller.getRecept(arg0);
            assertEquals(arg1, recept.getStap(recept.getStappen().size()).getBESCHRIJVING());
        } catch (InvocationTargetException | IllegalAccessException e) {
            Assertions.fail(e);
        }
    }

    @When("ik een bereidingsstap {string} {string} toevoeg aan recept met id {int} na stap {int}")
    public void ikEenBereidingsstapToevoegAanReceptMetIdNaStap(String arg0, String arg1, int arg2, int arg3) {
        try {
            Controller.addBereidingsStapToRecept(
                    arg2,
                    6,
                    arg0,
                    arg1,
                    arg3 + 1
            );
        } catch (InvocationTargetException | IllegalAccessException e) {
            Assertions.fail(e);
        }
    }

    @And("de bereidingsstap {int} voor recept {int} heeft beschrijving {string}")
    public void deBereidingsstapVoorReceptHeeftBeschrijving(int arg0, int arg1, String arg2) {
        try {
            Recept recept = Controller.getRecept(arg1);
            assertEquals(arg2, recept.getStap(arg0).getBESCHRIJVING());
        } catch (InvocationTargetException | IllegalAccessException e) {
            Assertions.fail(e);
        }
    }
}
