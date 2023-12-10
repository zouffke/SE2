package features;

import be.kdg.hifresh.applicationLayer.Controller;
import be.kdg.hifresh.businessLayer.aankoop.Product;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductSuggestiesRaadplegen {
    private List<Product> products;

    @When("ik de suggesties raadpleeg")
    public void ikDeSuggestiesRaadpleeg() {
        products = Controller.getProductSuggesties(Controller.getToday());
    }

    @Then("krijg ik een lijst van {int} producten, gesorteerd op wekelijkse gemiddelde aankoopprijs")
    public void krijgIkEenLijstVanProductenGesorteerdOpWekelijkseGemiddeldeAankoopprijs(int arg0) {
        assertEquals(arg0, products.size());
    }

    @And("product {int} in de lijst is {int}")
    public void productInDeLijstIs(int arg0, int arg1) {
        int id = this.products.get(arg1 - 1).getId();
        assertEquals(arg0, id);
    }
}
