package features;

import be.kdg.hifresh.applicationLayer.Controller;
import be.kdg.hifresh.businessLayer.domain.util.Munt;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@CucumberContextConfiguration
public class GemiddeldeAankoopprijsRaadplegen {
    private Munt avgResult;

    @When("ik de gemiddelde aankoopprijs van het recept {int} raadpleeg")
    public void ikDeGemiddeldeAankoopprijsVanHetReceptRaadpleeg(int arg0) {
        try {
            avgResult = Controller.getGemiddeldeAankoopPrijs(arg0, Controller.getToday());
        } catch (InvocationTargetException | IllegalAccessException e) {
            Assertions.fail(e);
        }
    }

    @Then("krijg ik dat de gemiddelde aankoopprijs van recept {int} gelijk is aan {double}")
    public void krijgIkDatDeGemiddeldeAankoopprijsVanReceptGelijkIsAan(int arg0, double arg1) {
        assertEquals(arg1, avgResult.getBedrag(), 0.0000001);
    }
}
