package be.kdg.hifresh.domain.recepten;

import be.kdg.hifresh.domain.aankoop.ContractManager;

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
        this.contractManager = new ContractManager();
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

    boolean addIngredientToRecept(int receptId, ){

    }
}