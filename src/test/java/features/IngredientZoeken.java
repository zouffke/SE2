package features;

import be.kdg.hifresh.applicationLayer.Controller;
import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientZoeken {
    private List<Product> products;

    @When("ik zoek op het woord {string}")
    public void ikZoekOpHetWoord(String arg0) {
        try {
            products = Controller.getProductsByName(arg0);
        } catch (InvocationTargetException | IllegalAccessException e) {
            Assertions.fail(e);
        }
    }


    @Then("krijg ik een lijst terug met {int} producten")
    public void krijgIkEenLijstTerugMetProducten(int arg0) {
        assertEquals(arg0, products.size());
    }


    @And("product_id van item {int} uit de lijst is {int}")
    public void product_idVanItemUitDeLijstIs(int arg0, int arg1) {
        assertEquals(arg1, products.get(arg0 - 1).getID());
    }


    @When("ik zoek naar producten die beschikbaar zijn")
    public void ikZoekNaarProductenDieBeschikbaarZijn() {
        products = Controller.getActiveProducts(Controller.getToday());
    }

    @When("ik zoek naar product die beschikbaar zijn, stijgend gesorteerd op gemiddelde aankoopprijs")
    public void ikZoekNaarProductDieBeschikbaarZijnStijgendGesorteerdOpGemiddeldeAankoopprijs() {
        LocalDate date = Controller.getToday();
        products = Controller.sortOnAvgPrice(Controller.getActiveProducts(date), date);
    }
}
