package be.kdg.hifresh.persistenceLayer.gebruiker;

import be.kdg.hifresh.businessLayer.domain.gebruiker.Leverancier;
import be.kdg.hifresh.persistenceLayer.Catalog;
import org.springframework.stereotype.Component;

@Component
public class LeverancierCataloog extends Catalog<Leverancier> {
    public LeverancierCataloog() {
        super();
    }
}
