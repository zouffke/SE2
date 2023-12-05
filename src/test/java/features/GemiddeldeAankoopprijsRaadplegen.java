package features;

import be.kdg.hifresh.dal.Controller;
import be.kdg.hifresh.dal.aankoop.ContractManager;
import be.kdg.hifresh.dal.recepten.ReceptManager;
import be.kdg.hifresh.domain.util.Eenheid;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GemiddeldeAankoopprijsRaadplegen {

    private LocalDate today;

    @BeforeAll
    static void beforeAll() {
        Controller.setManagers(new ContractManager(), new ReceptManager());
    }

    @Given("recepten")
    public void recepten(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> assertTrue(Controller.addReceptToCatalog(
                Integer.parseInt(row.get("recept_id")),
                row.get("recept_naam"),
                row.get("recept_beschrijving"))
        ));
    }

    @Given("subrecepten")
    public void subrecepten(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            try {
                assertTrue(Controller.addSubreceptToRecept(
                        Integer.parseInt(row.get("recept_id")),
                        Integer.parseInt(row.get("is_subrecept_van")))
                );
            } catch (InvocationTargetException | IllegalAccessException e) {
                Assertions.fail(e);
            }
        });
    }


    @Given("producten")
    public void producten(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> assertTrue(Controller.addProduct(
                Integer.parseInt(r.get("product_id")),
                r.get("product_naam"))
        ));
    }

    @Given("ingredienten")
    public void ingredienten(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> {
            try {
                assertTrue(Controller.addIngredientToRecept(
                        Integer.parseInt(r.get("ingredient_id")),
                        Integer.parseInt(r.get("product_id")),
                        Integer.parseInt(r.get("recept_id")),
                        Double.parseDouble(r.get("hoeveelheid")))
                );
            } catch (InvocationTargetException | IllegalAccessException e) {
                Assertions.fail(e);
            }
        });
    }

    @Given("bereidingsstappen")
    public void bereidingsstappen(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> {
            try {
                Controller.addBereidingsStapToRecept(
                        Integer.parseInt(r.get("recept_id")),
                        Integer.parseInt(r.get("bereidingsstap_id")),
                        r.get("bereidingsstap_naam"),
                        r.get("bereidingsstap_beschrijving")
                );
            } catch (InvocationTargetException | IllegalAccessException e) {
                Assertions.fail(e);
            }
        });
    }

    @Given("distributiecentra")
    public void distributiecentra(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> assertTrue(Controller.addCentrumToCatalog(
                Integer.parseInt(r.get("distributiecentrum_id")),
                r.get("distributiecentrum_naam"))
        ));
    }

    @Given("clausules")
    public void clausules(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> {
            try {
                assertTrue(Controller.addClausule(
                        Integer.parseInt(r.get("clausule_id")),
                        Integer.parseInt(r.get("product_id")),
                        LocalDateTime.parse(r.get("start_datum")),
                        LocalDateTime.parse(r.get("eind_datum")),
                        Double.parseDouble(r.get("hoeveelheid")),
                        Eenheid.Kilogram,
                        Double.parseDouble(r.get("aankoopprijs"))
                ));
            } catch (InvocationTargetException | IllegalAccessException e) {
                Assertions.fail(e);
            }
        });
    }

    @Given("het is vandaag {int}{int}{int}")
    public void hetIsVandaag(int arg0, int arg1, int arg2) {
        today = LocalDate.of(arg0, arg1, arg2);
    }


    @When("ik de gemiddelde aankoopprijs van het recept {int} raadpleeg")
    public void ikDeGemiddeldeAankoopprijsVanHetReceptRaadpleeg(int arg0) {
        //TODO
    }
}
