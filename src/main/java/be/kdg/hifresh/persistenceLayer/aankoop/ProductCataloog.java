package be.kdg.hifresh.persistenceLayer.aankoop;

import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import be.kdg.hifresh.persistenceLayer.Catalog;
import org.springframework.stereotype.Component;

@Component
public class ProductCataloog extends Catalog<Product> {
    public ProductCataloog() {
        super();
    }
}
