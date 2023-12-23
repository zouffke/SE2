package be.kdg.hifresh.businessLayer.services.aankoop.productSuggestions;

import be.kdg.hifresh.businessLayer.domain.aankoop.Product;

import java.time.LocalDate;
import java.util.List;

public interface IProductSuggestionsStrat {
    List<Product> getProductSuggesties(LocalDate date, List<Product> products);
}
