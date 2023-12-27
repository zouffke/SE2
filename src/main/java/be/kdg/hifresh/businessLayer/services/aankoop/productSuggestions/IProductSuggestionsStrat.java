package be.kdg.hifresh.businessLayer.services.aankoop.productSuggestions;

import be.kdg.hifresh.businessLayer.domain.aankoop.Product;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface defines a strategy for suggesting products.
 * It contains a method to get product suggestions based on a specific date and a list of products.
 */
public interface IProductSuggestionsStrat {

    /**
     * Provides product suggestions based on a specific date and a list of products.
     *
     * @param date The date for which product suggestions are needed.
     * @param products The list of products to consider for suggestions.
     * @return A list of suggested products.
     */
    List<Product> getProductSuggesties(LocalDate date, List<Product> products);
}