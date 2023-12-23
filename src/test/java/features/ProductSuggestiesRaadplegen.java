package features;

import be.kdg.hifresh.applicationLayer.Controller;
import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import be.kdg.hifresh.businessLayer.services.aankoop.productSuggestions.PercentDiffProductSuggestionsStrat;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@CucumberContextConfiguration
public class ProductSuggestiesRaadplegen {
    private List<Product> products;

    @When("ik de suggesties raadpleeg")
    public void ikDeSuggestiesRaadpleeg() {
        products = Controller.getProductSuggesties(new PercentDiffProductSuggestionsStrat(), Controller.getToday());
    }

    @Then("krijg ik een lijst van {int} producten, gesorteerd op wekelijkse gemiddelde aankoopprijs")
    public void krijgIkEenLijstVanProductenGesorteerdOpWekelijkseGemiddeldeAankoopprijs(int arg0) {
        assertEquals(arg0, products.size());
    }

    @And("product {int} in de lijst is {int}")
    public void productInDeLijstIs(int arg0, int arg1) {
        int id = this.products.get(arg1 - 1).getID();
        assertEquals(arg0, id);
    }
}
