package be.kdg.hifresh.domain.recepten;

import be.kdg.hifresh.domain.util.Munt;

import java.time.LocalDate;

public class ReceptController {

    //region vars
    private static ReceptManager receptManager;
    //endregion

    //region constructors
    private ReceptController() {
    }
    //endregion

    private static void checkReceptManger(){
        if (receptManager == null){
            receptManager = new ReceptManager();
        }
    }

    //TODO implement getGemiddeldeAankoopPrijs method
    public static Munt getGemiddeldeAankoopPrijs(int receptId, LocalDate date) {
        checkReceptManger();
        //! Function should return the value gotten from getGemiddeldeAankoopPrijs() from class ReceptManager
        //receptManager.
        return null;
    }

    public static boolean addReceptToCatalog(String name, int id, String beschrijving){
        return receptManager.addReceptToCataloog(receptManager.createRecept(name, id, beschrijving));
    }

    public static boolean addSubreceptToRecept(int subReceptId, int receptId){
        return receptManager.addSubreceptToSubrecept(subReceptId, receptId);
    }
}