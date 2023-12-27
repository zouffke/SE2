package be.kdg.hifresh.persistenceLayer.recepten;

import be.kdg.hifresh.businessLayer.domain.recepten.Ingredient;
import be.kdg.hifresh.persistenceLayer.Catalog;
import org.springframework.stereotype.Component;

/**
 * A catalog class for managing Ingredient objects.
 *
 * @author Dandois Luca
 */
@Component
public class IngredientCataloog extends Catalog<Ingredient> {

    /**
     * Constructor for IngredientCataloog.
     *
     * @author Dandois Luca
     */
    public IngredientCataloog() {
        super();
    }
}