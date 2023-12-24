package be.kdg.hifresh;

import be.kdg.hifresh.applicationLayer.aankoop.AankoopController;
import be.kdg.hifresh.applicationLayer.gebruiker.GebruikerController;
import be.kdg.hifresh.applicationLayer.recepten.ReceptController;
import be.kdg.hifresh.businessLayer.domain.recepten.Recept;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BereidingsStapToevoegen {

    //region vars

    @Autowired
    ReceptController receptController;

    //endregion

    @When("ik een bereidingsstap {string} {string} toevoeg aan recept met id {int}")
    public void ikEenBereidingsstapToevoegAanReceptMetId(String arg0, String arg1, int arg2) {
        receptController.addBereidingsStapToRecept(
                arg2,
                6,
                arg0,
                arg1
        );
    }

    @Then("heeft het recept {int}, {int} bereidingsstappen")
    public void heeftHetReceptBereidingsstappen(int arg0, int arg1) {
        assertEquals(arg1, receptController.getRecept(arg0).getStappen().size());
    }

    @And("de laatste bereidingstap voor recept {int} heeft beschrijving {string}")
    public void deLaatsteBereidingstapVoorReceptHeeftBeschrijving(int arg0, String arg1) {
        Recept recept = receptController.getRecept(arg0);
        assertEquals(arg1, recept.getStap(recept.getStappen().size()).getBESCHRIJVING());
    }

    @When("ik een bereidingsstap {string} {string} toevoeg aan recept met id {int} na stap {int}")
    public void ikEenBereidingsstapToevoegAanReceptMetIdNaStap(String arg0, String arg1, int arg2, int arg3) {
        receptController.addBereidingsStapToRecept(
                arg2,
                6,
                arg0,
                arg1,
                arg3 + 1
        );
    }

    @And("de bereidingsstap {int} voor recept {int} heeft beschrijving {string}")
    public void deBereidingsstapVoorReceptHeeftBeschrijving(int arg0, int arg1, String arg2) {
        Recept recept = receptController.getRecept(arg1);
        assertEquals(arg2, recept.getStap(arg0).getBESCHRIJVING());
    }
}
