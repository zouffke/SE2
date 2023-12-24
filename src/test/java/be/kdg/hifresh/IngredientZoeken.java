package be.kdg.hifresh;

import be.kdg.hifresh.applicationLayer.aankoop.AankoopController;
import be.kdg.hifresh.applicationLayer.gebruiker.GebruikerController;
import be.kdg.hifresh.applicationLayer.recepten.ReceptController;
import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import be.kdg.hifresh.persistenceLayer.memory.MemoryRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IngredientZoeken {

    //region vars

    private List<Product> products;
    @Autowired
    AankoopController aankoopController;
    @Autowired
    MemoryRepository memoryRepository;

    //endregion

    @When("ik zoek op het woord {string}")
    public void ikZoekOpHetWoord(String arg0) {
        try {
            products = aankoopController.getProductsByName(arg0);
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
        products = aankoopController.getActiveProducts((LocalDate) memoryRepository.get(LocalDate.class));
    }

    @When("ik zoek naar product die beschikbaar zijn, stijgend gesorteerd op gemiddelde aankoopprijs")
    public void ikZoekNaarProductDieBeschikbaarZijnStijgendGesorteerdOpGemiddeldeAankoopprijs() {
        LocalDate date = (LocalDate) memoryRepository.get(LocalDate.class);
        products = aankoopController.sortOnAvgPrice(aankoopController.getActiveProducts(date), date);
    }
}
