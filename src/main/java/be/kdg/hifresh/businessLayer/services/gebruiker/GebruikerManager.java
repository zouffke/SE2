package be.kdg.hifresh.businessLayer.services.gebruiker;

import be.kdg.hifresh.businessLayer.domain.gebruiker.Leverancier;
import be.kdg.hifresh.businessLayer.services.pubSub.MessageBroker;
import be.kdg.hifresh.persistenceLayer.Catalog;
import be.kdg.hifresh.businessLayer.services.Manager;
import be.kdg.hifresh.persistenceLayer.gebruiker.LeverancierCataloog;
import lombok.Getter;

@Getter
public class GebruikerManager extends Manager {
    //region vars
    private final Catalog<Leverancier> LEVERANCIER_CATALOG;
    private final MessageBroker messageBroker;
    //endregion

    //region constructors
    public GebruikerManager(MessageBroker messageBroker) {
        this.LEVERANCIER_CATALOG = new LeverancierCataloog();
        this.messageBroker = messageBroker;
    }
    //endregion


    @Override
    public <T> boolean add(T object, Catalog<T> catalog) {
        messageBroker.subscribe(object);
        return super.add(object, catalog);
    }
}
