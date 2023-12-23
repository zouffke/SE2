package features;

import be.kdg.hifresh.applicationLayer.Controller;
import be.kdg.hifresh.businessLayer.util.Eenheid;
import be.kdg.hifresh.persistenceLayer.aankoop.AankoopManager;
import be.kdg.hifresh.persistenceLayer.gebruiker.GebruikerManager;
import be.kdg.hifresh.persistenceLayer.recepten.ReceptManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Givens {
    @Given("producten")
    public void producten(DataTable dataTable) {
        beforeAll();
        dataTable.asMaps().forEach(r -> assertTrue(Controller.addProduct(
                Integer.parseInt(r.get("product_id")),
                r.get("product_naam"))
        ));
    }

    @BeforeAll
    static void beforeAll() {
        Controller.setManagers(new AankoopManager(), new ReceptManager(), new GebruikerManager());
    }

    @Given("distributiecentra")
    public void distributiecentra(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> assertTrue(Controller.addCentrum(
                Integer.parseInt(r.get("distributiecentrum_id")),
                r.get("distributiecentrum_naam"))
        ));
    }


    @Given("leveranciers")
    public void leveranciers(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> assertTrue(Controller.addLeverancier(
                Integer.parseInt(r.get("leverancier_id")),
                r.get("leverancier_naam")
        )));
    }

    @Given("recepten")
    public void recepten(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> assertTrue(Controller.addRecept(
                Integer.parseInt(row.get("recept_id")),
                row.get("recept_naam"),
                row.get("recept_beschrijving"))
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
                        Double.parseDouble(r.get("hoeveelheid")),
                        Eenheid.valueOf(r.get("eenheid").toUpperCase()))
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

                List<String> ingredientIds = this.getItemFromList(r.get("ingredient_ids"));
                if (!ingredientIds.get(0).equals("-")) {
                    Controller.addIngredientToBereidingstap(
                            Integer.parseInt(r.get("recept_id")),
                            Integer.parseInt(r.get("volgnummer")),
                            ingredientIds.stream().map(Integer::parseInt).toList()
                    );
                }

            } catch (InvocationTargetException | IllegalAccessException e) {
                Assertions.fail(e);
            }
        });
    }

    private List<String> getItemFromList(String list) {
        return getItemFromList(list, ",");
    }

    private List<String> getItemFromList(String list, String delim) {
        return Arrays.stream(list.split(delim)).toList();
    }

    @Given("subrecepten")
    public void subrecepten(DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            try {
                assertTrue(Controller.addSubreceptToRecept(
                        Integer.parseInt(row.get("recept_id")),
                        Integer.parseInt(row.get("is_subrecept_van")),
                        Integer.parseInt(row.get("invoegen_na_stap")))
                );
            } catch (InvocationTargetException | IllegalAccessException e) {
                Assertions.fail(e);
            }
        });
    }

    @Given("contract")
    public void contract(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> {
            try {
                assertTrue(Controller.addContract(
                        Integer.parseInt(r.get("contract_id")),
                        Integer.parseInt(r.get("product_id")),
                        Integer.parseInt(r.get("leverancier_id")),
                        Integer.parseInt(r.get("distributiecentrum_id"))
                ));
            } catch (InvocationTargetException | IllegalAccessException e) {
                Assertions.fail(e);
            }
        });
    }

    @Given("clausules")
    public void clausules(DataTable dataTable) {
        dataTable.asMaps().forEach(r -> {
            try {
                assertTrue(Controller.addClausule(
                        Integer.parseInt(r.get("clausule_id")),
                        Integer.parseInt(r.get("contract_id")),
                        LocalDate.parse(r.get("start_datum")),
                        LocalDate.parse(r.get("eind_datum")),
                        Double.parseDouble(r.get("hoeveelheid")),
                        Eenheid.valueOf(r.get("eenheid").toUpperCase()),
                        Double.parseDouble(r.get("aankoopprijs"))
                ));
            } catch (InvocationTargetException | IllegalAccessException e) {
                Assertions.fail(e);
            }
        });
    }

    @Given("het is vandaag {string}-{string}-{string}")
    public void hetIsVandaag(String arg0, String arg1, String arg2) {
        Controller.setToday(LocalDate.of(Integer.parseInt(arg0), Integer.parseInt(arg1), Integer.parseInt(arg2)));
    }

    @Given("we zoeken op datum van {string}-{string}-{string}")
    public void weZoekenOpDatumVan(String arg0, String arg1, String arg2) {
        Controller.setToday(LocalDate.of(Integer.parseInt(arg0), Integer.parseInt(arg1), Integer.parseInt(arg2)));
    }
}
