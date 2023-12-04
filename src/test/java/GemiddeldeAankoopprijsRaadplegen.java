import be.kdg.hifresh.domain.recepten.ReceptController;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

public class GemiddeldeAankoopprijsRaadplegen {

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
}
