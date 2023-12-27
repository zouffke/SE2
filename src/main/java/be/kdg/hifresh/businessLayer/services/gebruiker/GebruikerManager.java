package be.kdg.hifresh.businessLayer.services.gebruiker;

import be.kdg.hifresh.businessLayer.services.Manager;
import be.kdg.hifresh.businessLayer.services.pubSub.MessageBroker;
import be.kdg.hifresh.persistenceLayer.Catalog;
import be.kdg.hifresh.persistenceLayer.gebruiker.LeverancierCataloog;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for managing users.
 * It extends the Manager class and provides methods to add users and subscribe them to a message broker.
 */
@Getter
@Service
public class GebruikerManager extends Manager {
    //region vars

    /**
     * MessageBroker instance for managing messages
     */
    private final MessageBroker messageBroker;

    //endregion

    //region constructors

    /**
     * Constructor for GebruikerManager.
     * Initializes the user catalog and message broker.
     *
     * @param leverancierCataloog Catalog of users
     * @param messageBroker Message broker for managing messages
     */
    @Autowired
    public GebruikerManager(LeverancierCataloog leverancierCataloog,
                            MessageBroker messageBroker) {
        super();
        super.addCatalog(leverancierCataloog);
        this.messageBroker = messageBroker;
    }

    //endregion

    /**
     * Adds a user to the catalog and subscribes them to the message broker.
     *
     * @param object The user to be added
     * @param catalog The catalog to which the user is to be added
     * @return true if the user was added successfully, false otherwise
     */
    @Override
    public <T> boolean add(T object, Catalog<T> catalog) {
        messageBroker.subscribe(object);
        return super.add(object, catalog);
    }
}