package be.kdg.hifresh.persistenceLayer.aankoop;

import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import be.kdg.hifresh.persistenceLayer.Catalog;
import org.springframework.stereotype.Component;

/**
 * This class represents a catalog of products.
 * It extends the Catalog class and is used to manage a collection of Product objects.
 * It is annotated as a Spring component, so it can be automatically detected and instantiated by Spring.
 */
@Component
public class ProductCataloog extends Catalog<Product> {

    /**
     * Default constructor for ProductCataloog.
     * Calls the parent class constructor to initialize the catalog.
     */
    public ProductCataloog() {
        super();
    }
}