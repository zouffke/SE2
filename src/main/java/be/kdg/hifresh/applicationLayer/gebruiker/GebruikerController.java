package be.kdg.hifresh.applicationLayer.gebruiker;

import be.kdg.hifresh.businessLayer.domain.gebruiker.GebruikerFactory;
import be.kdg.hifresh.businessLayer.domain.gebruiker.Leverancier;
import be.kdg.hifresh.businessLayer.services.gebruiker.GebruikerManager;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public final class GebruikerController {
    //region vars
    private final GebruikerManager MANAGER;

    //endregion

    //region constructors

    @Autowired
    public GebruikerController(GebruikerManager manager) {
        this.MANAGER = manager;
    }

    //endregion

    //region setup functions
    public boolean addLeverancier(int id, String name) {
        return MANAGER.add(GebruikerFactory.createLeverancier(id, name), MANAGER.getLEVERANCIER_CATALOG());
    }
    //endregion

    public Leverancier getLeverancier(int leverancierId) {
        return MANAGER.getById(leverancierId, MANAGER.getLEVERANCIER_CATALOG());
    }
}
