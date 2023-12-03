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


    @Given("ingredienten")
    public void ingredienten(DataTable dataTable) {

    }
}
