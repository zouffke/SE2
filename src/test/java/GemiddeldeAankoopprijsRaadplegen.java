import be.kdg.hifresh.domain.recepten.ReceptController;
import be.kdg.hifresh.domain.util.Eenheid;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GemiddeldeAankoopprijsRaadplegen {

    private LocalDate today;

    @Given("recepten")
    public void recepten(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> ReceptController.addReceptToCatalog(
                row.get("recept_naam"),
                Integer.parseInt(row.get("recept_id")),
                row.get("recept_beschrijving")
        ));
    }

    @Given("subrecepten")
    public void subrecepten(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> ReceptController.addSubreceptToRecept(
                Integer.parseInt(row.get("recept_id")),
                Integer.parseInt(row.get("is_subrecept_van"))
        ));
    }


    @Given("producten")
    public void producten(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> ReceptController.addProduct(
                Integer.parseInt(r.get("product_id")),
                r.get("product_naam")
        ));
    }

    @Given("ingredienten")
    public void ingredienten(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> ReceptController.addIngredientToRecept(
                Integer.parseInt(r.get("ingredient_id")),
                Integer.parseInt(r.get("product_id")),
                Integer.parseInt(r.get("recept_id")),
                Double.parseDouble(r.get("hoeveelheid"))
        ));
    }

    @Given("bereidingsstappen")
    public void bereidingsstappen(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> ReceptController.addBereidingsStapToRecept(
                Integer.parseInt(r.get("recept_id")),
                Integer.parseInt(r.get("bereidingsstap_id")),
                r.get("bereidingsstap_naam"),
                r.get("bereidingsstap_beschrijving")
        ));
    }

    @Given("distributiecentra")
    public void distributiecentra(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> ReceptController.addCentrumToCatalog(
                Integer.parseInt(r.get("distributiecentrum_id")),
                r.get("distributiecentrum_naam")
        ));
    }

    @Given("clausules")
    public void clausules(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> ReceptController.addClausule(
                Integer.parseInt(r.get("clausule_id")),
                Integer.parseInt(r.get("product_id")),
                LocalDateTime.parse(r.get("start_datum")),
                LocalDateTime.parse(r.get("eind_datum")),
                Double.parseDouble(r.get("hoeveelheid")),
                Eenheid.Kilogram,
                Double.parseDouble(r.get("aankoopprijs"))
        ));
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
