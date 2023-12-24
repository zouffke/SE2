package be.kdg.hifresh;

import be.kdg.hifresh.applicationLayer.aankoop.AankoopController;
import be.kdg.hifresh.applicationLayer.gebruiker.GebruikerController;
import be.kdg.hifresh.applicationLayer.recepten.ReceptController;
import be.kdg.hifresh.businessLayer.domain.util.Munt;
import be.kdg.hifresh.persistenceLayer.memory.MemoryRepository;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GemiddeldeAankoopprijsRaadplegen {

    //region vars

    private Munt avgResult;

    @Autowired
    ReceptController receptController;
    @Autowired
    MemoryRepository memoryRepository;

    //endregion

    @When("ik de gemiddelde aankoopprijs van het recept {int} raadpleeg")
    public void ikDeGemiddeldeAankoopprijsVanHetReceptRaadpleeg(int arg0) {
            avgResult = receptController.getGemiddeldeAankoopPrijs(arg0, (LocalDate) memoryRepository.get(LocalDate.class));
    }

    @Then("krijg ik dat de gemiddelde aankoopprijs van recept {int} gelijk is aan {double}")
    public void krijgIkDatDeGemiddeldeAankoopprijsVanReceptGelijkIsAan(int arg0, double arg1) {
        assertEquals(arg1, avgResult.getBEDRAG(), 0.0000001);
    }
}
