package be.kdg.hifresh.domain.recepten;

import be.kdg.hifresh.domain.aankoop.AankoopFactory;
import be.kdg.hifresh.dal.aankoop.ContractManager;
import be.kdg.hifresh.domain.util.Eenheid;

import java.time.LocalDateTime;

/**
 * Startup die ingredienten en recepten aan huis levert voor een week lekker zelf koken.
 */
public class ReceptManager {
    //region vars
    private final ReceptCataloog receptCataloog;
    private final IngredientCataloog ingredientCataloog;
    private final ContractManager contractManager;
    //endregion

    ReceptManager() {
        this.receptCataloog = new ReceptCataloog();
        this.ingredientCataloog = new IngredientCataloog();
        this.contractManager = AankoopFactory.createContractManager();
    }

    boolean addReceptToCataloog(Recept recept) {
        return receptCataloog.addRecept(recept);
    }

    Recept createRecept(String naam, int id, String beschrijving) {
        return new Recept(naam, id, beschrijving);
    }

    boolean addSubreceptToSubrecept(int subReceptId, int receptId) {
        return this.receptCataloog.getRecept(receptId)
                .addSubrecept(this.receptCataloog.getRecept(subReceptId));
    }

    boolean addProduct(int prodId, String name) {
        return this.contractManager
                .addContractToCatalog(
                        AankoopFactory.createContract(
                                AankoopFactory.createProduct(prodId, name)
                        )
                );
    }

    boolean addIngredientToProd(int ingrId, int prodId, int receptId, double amt) {
        Recept recept = this.receptCataloog.getRecept(receptId);
        if (recept == null) {
            throw new RuntimeException("Given prod id can not be found in list");
        }

        Ingredient ingredient = this.ingredientCataloog.getIngredient(ingrId);
        if (ingredient == null) {
            ingredient = this.ingredientCataloog.addIngredient(
                    this.ingredientCataloog.createNewIngredient(
                            ingrId,
                            this.contractManager.getProductFromCatalog(prodId),
                            amt
                    ));
        }

        return recept.addIngredient(ingredient);
    }

    void addStapToRecept(int receptId, int stapId, String stapName, String stapBesch) {
        Recept recept = this.receptCataloog.getRecept(receptId);
        if (recept == null) {
            throw new RuntimeException("Given prod id can not be found in list");
        }

        recept.addBereidingsStap(new BereidingsStap(stapName, stapBesch, stapId));
    }

    boolean addCentrumToCatalog(int id, String name) {
        return this.contractManager.addCentrumToCatalog(id, name);
    }

    boolean addClausuleToContract(int id, int prodId, LocalDateTime start, LocalDateTime end, double hoeveelheid, Eenheid eenheid, double bedrag) {
        return this.contractManager.addClausuleToContract(prodId, this.contractManager.createNewClausule(id, prodId, start, end, hoeveelheid, eenheid, bedrag));
    }
}