package be.kdg.hifresh.dal.recepten;

import be.kdg.hifresh.domain.util.Eenheid;
import be.kdg.hifresh.domain.util.Munt;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    public static Munt getGemiddeldeAankoopPrijs(int receptId, LocalDate date) {
        //! Function should return the value gotten from getGemiddeldeAankoopPrijs() from class ReceptManager
        //receptManager.
        return null;
    }

    public static boolean addReceptToCatalog(String name, int id, String beschrijving) {
        return manager.addReceptToCataloog(manager.createRecept(name, id, beschrijving));
    }

    public static boolean addSubreceptToRecept(int subReceptId, int receptId) {
        return manager.addSubreceptToSubrecept(subReceptId, receptId);
    }

    public static boolean addProduct(int prodId, String name) {
        return manager.addProduct(prodId, name);
    }

    public static boolean addIngredientToRecept(int ingrId, int prodId, int receptId, double amt) {
        return manager.addIngredientToProd(ingrId, prodId, receptId, amt);
    }

    public static void addBereidingsStapToRecept(int receptId, int stapId, String stapName, String stapBesch) {
        manager.addStapToRecept(receptId, stapId, stapName, stapBesch);
    }

    public static boolean addCentrumToCatalog(int id, String name) {
        return manager.addCentrumToCatalog(id, name);
    }

    public static boolean addClausule(int id, int prodId, LocalDateTime start, LocalDateTime end, double hoeveelheid, Eenheid eenheid, double bedrag) {
        return manager.addClausuleToContract(id, prodId, start, end, hoeveelheid, eenheid, bedrag);
    }
}