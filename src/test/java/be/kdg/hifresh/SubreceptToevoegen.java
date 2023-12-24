package be.kdg.hifresh;

import be.kdg.hifresh.applicationLayer.recepten.ReceptController;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SubreceptToevoegen {

    //region vars

    @Autowired
    ReceptController receptController;

    //endregion

    @When("ik het recept {int} toevoeg aan recept met id {int} na stap {int}")
    public void ikHetReceptToevoegAanReceptMetIdNaStap(int arg0, int arg1, int arg2) {
        assertTrue(receptController.addSubreceptToRecept(arg0, arg1, arg2));
    }


    @Then("heeft recept {int} een subrecept met id {int}")
    public void heeftReceptEenSubreceptMetId(int arg0, int arg1) {

        assertEquals(arg1, receptController.getRecept(arg0).getSubrecepts().get(0).getID());

    }
}
