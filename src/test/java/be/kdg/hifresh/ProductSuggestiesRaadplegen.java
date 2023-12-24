package be.kdg.hifresh;

import be.kdg.hifresh.applicationLayer.aankoop.AankoopController;
import be.kdg.hifresh.applicationLayer.gebruiker.GebruikerController;
import be.kdg.hifresh.applicationLayer.recepten.ReceptController;
import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import be.kdg.hifresh.businessLayer.services.aankoop.productSuggestions.PercentDiffProductSuggestionsStrat;
import be.kdg.hifresh.persistenceLayer.memory.MemoryRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductSuggestiesRaadplegen {

    //region vars

    private List<Product> products;
    @Autowired
    AankoopController aankoopController;
    @Autowired
    MemoryRepository memoryRepository;

    //endregion

    @When("ik de suggesties raadpleeg")
    public void ikDeSuggestiesRaadpleeg() {
        products = aankoopController.getProductSuggesties(new PercentDiffProductSuggestionsStrat(), (LocalDate) memoryRepository.get(LocalDate.class));
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
