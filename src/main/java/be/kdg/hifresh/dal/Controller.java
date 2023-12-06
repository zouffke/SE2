package be.kdg.hifresh.dal;

import be.kdg.hifresh.dal.aankoop.ContractController;
import be.kdg.hifresh.dal.aankoop.ContractManager;
import be.kdg.hifresh.dal.recepten.ReceptController;
import be.kdg.hifresh.dal.recepten.ReceptManager;
import be.kdg.hifresh.domain.util.Eenheid;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

public final class Controller {

    //region constructors

    private Controller() {
    }

    //endregion

    public static void setManagers(ContractManager contractManager, ReceptManager receptManager) {
        ContractController.setManager(contractManager);
        ReceptController.setManager(receptManager);
    }

    //region controller functions setup

    public static boolean addReceptToCatalog(int id, String name, String beschrijving) {
        return ReceptController.addReceptToCatalog(id, name, beschrijving);
    }

    public static boolean addSubreceptToRecept(int subReceptId, int receptId) throws InvocationTargetException, IllegalAccessException {
        return ReceptController.addSubreceptToRecept(subReceptId, receptId);
    }

    public static boolean addProduct(int prodId, String name) {
        return ContractController.addProduct(prodId, name);
    }

    public static boolean addIngredientToRecept(int ingrId, int prodId, int receptId, double amt) throws InvocationTargetException, IllegalAccessException {
        return ReceptController.addIngredientToRecept(ingrId, ContractController.getProductFromCatalog(prodId), receptId, amt);
    }

    public static void addBereidingsStapToRecept(int receptId, int stapId, String stapName, String stapBesch) throws InvocationTargetException, IllegalAccessException {
        ReceptController.addBereidingsStapToRecept(receptId, stapId, stapName, stapBesch);
    }

    public static boolean addCentrumToCatalog(int id, String name) {
        return ContractController. addCentrumToCatalog(id, name);
    }

    public static boolean addClausule(int id, int prodId, LocalDateTime start, LocalDateTime end, double hoeveelheid, Eenheid eenheid, double bedrag) throws InvocationTargetException, IllegalAccessException {
        return ContractController.addClausule(id, prodId, start, end, hoeveelheid, eenheid, bedrag);
    }

    //endregion
}
