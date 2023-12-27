package be.kdg.hifresh;

import be.kdg.hifresh.applicationLayer.aankoop.AankoopController;
import be.kdg.hifresh.applicationLayer.gebruiker.GebruikerController;
import be.kdg.hifresh.applicationLayer.recepten.ReceptController;
import be.kdg.hifresh.businessLayer.domain.util.Eenheid;
import be.kdg.hifresh.persistenceLayer.memory.MemoryRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@CucumberContextConfiguration
public class Givens {

    //region vars

    @Autowired
    AankoopController aankoopController;
    @Autowired
    GebruikerController gebruikerController;
    @Autowired
    ReceptController receptController;
    @Autowired
    MemoryRepository memoryRepository;

    //endregion


    private void beforeAll() {
        aankoopController.clearCatalogs();
        gebruikerController.clearCatalogs();
        receptController.clearCatalogs();
    }

    @Given("producten")
    public void producten(DataTable dataTable) {
        beforeAll();
        dataTable.asMaps().forEach(r -> assertTrue(aankoopController.addProduct(
                Integer.parseInt(r.get("product_id")),
                r.get("product_naam"))
        ));
    }

    @Given("distributiecentra")
    public void distributiecentra(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> assertTrue(aankoopController.addCentrum(
                Integer.parseInt(r.get("distributiecentrum_id")),
                r.get("distributiecentrum_naam"))
        ));
    }


    @Given("leveranciers")
    public void leveranciers(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> assertTrue(gebruikerController.addLeverancier(
                Integer.parseInt(r.get("leverancier_id")),
                r.get("leverancier_naam")
        )));
    }

    @Given("recepten")
    public void recepten(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> assertTrue(receptController.addRecept(
                Integer.parseInt(row.get("recept_id")),
                row.get("recept_naam"),
                row.get("recept_beschrijving"))
        ));
    }

    @Given("ingredienten")
    public void ingredienten(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> assertTrue(receptController.addIngredient(
                Integer.parseInt(r.get("ingredient_id")),
                Integer.parseInt(r.get("product_id")),
                Double.parseDouble(r.get("hoeveelheid")),
                Eenheid.valueOf(r.get("eenheid").toUpperCase()))
        ));
    }

    @Given("bereidingsstappen")
    public void bereidingsstappen(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> {
            receptController.addBereidingsStapToRecept(
                    Integer.parseInt(r.get("recept_id")),
                    Integer.parseInt(r.get("bereidingsstap_id")),
                    r.get("bereidingsstap_naam"),
                    r.get("bereidingsstap_beschrijving")

            );

            List<String> ingredientIds = this.getItemFromList(r.get("ingredient_ids"));
            if (!ingredientIds.get(0).equals("-")) {
                receptController.addIngredientToBereidingstap(
                        Integer.parseInt(r.get("recept_id")),
                        Integer.parseInt(r.get("volgnummer")),
                        ingredientIds.stream().map(Integer::parseInt).toList()
                );
            }

        });
    }

    private List<String> getItemFromList(String list) {
        return Arrays.stream(list.split(",")).toList();
    }

    @Given("subrecepten")
    public void subrecepten(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            assertTrue(receptController.addSubreceptToRecept(
                    Integer.parseInt(row.get("recept_id")),
                    Integer.parseInt(row.get("is_subrecept_van")),
                    Integer.parseInt(row.get("invoegen_na_stap")))
            );
        });
    }

    @Given("contract")
    public void contract(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> {
            assertTrue(aankoopController.addContract(
                    Integer.parseInt(r.get("contract_id")),
                    Integer.parseInt(r.get("product_id")),
                    Integer.parseInt(r.get("leverancier_id")),
                    Integer.parseInt(r.get("distributiecentrum_id"))
            ));
        });
    }

    @Given("clausules")
    public void clausules(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> {
            try {
                assertTrue(aankoopController.addClausule(
                        Integer.parseInt(r.get("clausule_id")),
                        Integer.parseInt(r.get("contract_id")),
                        LocalDate.parse(r.get("start_datum")),
                        LocalDate.parse(r.get("eind_datum")),
                        Double.parseDouble(r.get("hoeveelheid")),
                        Eenheid.valueOf(r.get("eenheid").toUpperCase()),
                        Double.parseDouble(r.get("aankoopprijs"))
                ));
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                Assertions.fail(e);
            }
        });
    }

    //TODO
    @Given("het is vandaag {string}-{string}-{string}")
    public void hetIsVandaag(String arg0, String arg1, String arg2) {
        memoryRepository.add(LocalDate.of(Integer.parseInt(arg0), Integer.parseInt(arg1), Integer.parseInt(arg2)));
    }

    @Given("we zoeken op datum van {string}-{string}-{string}")
    public void weZoekenOpDatumVan(String arg0, String arg1, String arg2) {
        memoryRepository.add(LocalDate.of(Integer.parseInt(arg0), Integer.parseInt(arg1), Integer.parseInt(arg2)));
    }
}
