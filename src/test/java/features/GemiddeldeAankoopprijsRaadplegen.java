package features;

import be.kdg.hifresh.dal.Controller;
import be.kdg.hifresh.domain.util.Munt;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
