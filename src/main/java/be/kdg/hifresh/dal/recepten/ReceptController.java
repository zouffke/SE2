package be.kdg.hifresh.dal.recepten;

import be.kdg.hifresh.domain.aankoop.Product;
import be.kdg.hifresh.domain.recepten.Recept;
import be.kdg.hifresh.domain.recepten.ReceptenFactory;
import be.kdg.hifresh.domain.util.Eenheid;
import be.kdg.hifresh.domain.util.Munt;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public final class ReceptController {

    //region vars
    @Setter
    private static ReceptManager manager;
    //endregion

    //region constructors
    private ReceptController() {
    }
    //endregion

    //TODO implement getGemiddeldeAankoopPrijs method
    public static List<Product> getAllProducts(int receptId) throws InvocationTargetException, IllegalAccessException {
        return manager.getAllProducts(receptId);
    }

    //region setup functions

    public static boolean addReceptToCatalog(int id, String name, String beschrijving) {
        return manager.addObjtoCatalog(
                ReceptenFactory.createRecept(id, name, beschrijving),
                manager.getReceptCataloog()
        );
    }

    public static boolean addSubreceptToRecept(int subReceptId, int receptId) throws InvocationTargetException, IllegalAccessException {
        return manager.getObjFromCatalogById(
                receptId,
                manager.getReceptCataloog()
        ).addSubrecept(
                manager.getObjFromCatalogById(
                        subReceptId,
                        manager.getReceptCataloog())
        );
    }

    public static boolean addIngredientToRecept(int ingrId, Product product, int receptId, double amt) throws InvocationTargetException, IllegalAccessException {
        return manager.getObjFromCatalogById(
                        receptId,
                        manager.getReceptCataloog())
                .addIngredient(
                        ReceptenFactory.createIngredient(
                                ingrId,
                                product,
                                amt
                        )
                );
    }

    public static void addBereidingsStapToRecept(int receptId, int stapId, String stapName, String stapBesch) throws InvocationTargetException, IllegalAccessException {
        Recept recept = manager.getObjFromCatalogById(
                receptId,
                manager.getReceptCataloog());

        recept.addBereidingsStap(
                ReceptenFactory.createBereidingsStap(
                        stapId,
                        stapName,
                        stapBesch
                ),
                recept.getNextVolgnummer()
        );
    }

    //endregion
}