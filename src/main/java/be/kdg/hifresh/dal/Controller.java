package be.kdg.hifresh.dal;

import be.kdg.hifresh.dal.aankoop.ContractController;
import be.kdg.hifresh.dal.aankoop.ContractManager;
import be.kdg.hifresh.dal.recepten.ReceptController;
import be.kdg.hifresh.dal.recepten.ReceptManager;

public final class Controller {

    //region constructors

    private Controller() {
    }

    //endregion

    public static void setManagers(ContractManager contractManager, ReceptManager receptManager){
        ContractController.setManager(contractManager);
        ReceptController.setManager(receptManager);
    }
}
