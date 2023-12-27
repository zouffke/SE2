package be.kdg.hifresh.businessLayer.services.aankoop;

import be.kdg.hifresh.businessLayer.domain.aankoop.Contract;
import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import be.kdg.hifresh.businessLayer.domain.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.domain.util.Munt;
import be.kdg.hifresh.businessLayer.services.Manager;
import be.kdg.hifresh.businessLayer.services.aankoop.productSuggestions.IProductSuggestionsStrat;
import be.kdg.hifresh.persistenceLayer.aankoop.ContractCataloog;
import be.kdg.hifresh.persistenceLayer.aankoop.DistributieCentraCataloog;
import be.kdg.hifresh.persistenceLayer.aankoop.ProductCataloog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

/**
 * A manager class for handling contracts.
 * Extends the Manager class.
 * It provides methods to get average purchase price, get active products, get average weekly and yearly purchase price,
 * get percentage difference, sort products based on score and get product suggestions.
 */
@Service
public class AankoopManager extends Manager {

    /**
     * Constructor for ContractManager.
     * Initializes the contract catalog and distribution center catalog.
     */
    @Autowired
    public AankoopManager(
            ContractCataloog contractCataloog,
            DistributieCentraCataloog distributieCentraCataloog,
            ProductCataloog productCataloog) {
        super();
        super.addCatalog(contractCataloog);
        super.addCatalog(distributieCentraCataloog);
        super.addCatalog(productCataloog);
    }

    /**
     * Calculates the average purchase price for a list of ingredients on a given date.
     *
     * @param ingredients List of ingredients.
     * @param date        The date for which to calculate the average purchase price.
     * @return The average purchase price.
     */
    public Munt getGemiddeldeAankoopPrijs(List<Ingredient> ingredients, LocalDate date) {
        return GemiddeldeAankoopPrijs.getGemiddeldeAankoopPrijs(ingredients, date);
    }

    /**
     * Provides product suggestions for a given date.
     *
     * @param date The date for which to provide product suggestions.
     * @return A list of product suggestions.
     */
    public List<Product> getProductSuggesties(IProductSuggestionsStrat productSuggestionsStrat, LocalDate date) {
        return productSuggestionsStrat.getProductSuggesties(date, getActiveProducts(date));
    }

    /**
     * Retrieves a list of active products on a given date.
     *
     * @param date The date for which to retrieve the active products.
     * @return A list of active products.
     */
    public List<Product> getActiveProducts(LocalDate date) {
        return super.getCatalog(ContractCataloog.class)
                .getList()
                .stream()
                .filter(contract -> contract.getCLAUSULES() != null && contract.getCLAUSULES().stream().anyMatch(clausule -> clausule.isActive(date)))
                .map(Contract::getPRODUCT)
                .distinct()
                .toList();
    }

    /**
     * Retrieves a list of products by their name.
     *
     * @param name The name of the products to retrieve.
     * @return A list of products with the given name.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getTByName(String name, Class<T> object) throws InvocationTargetException, IllegalAccessException {
        if (object == Product.class) {
            return (List<T>) super.getCatalog(ProductCataloog.class)
                    .getByName(name);
        }
        return null;
    }

    /**
     * Sorts a list of products based on their average price on a given date.
     *
     * @param list The list of products to sort.
     * @param date The date for which to calculate the average price.
     * @return A sorted list of products.
     */
    public List<Product> sortOnAvgPrice(List<Product> list, LocalDate date) {
        return list.stream().sorted(Comparator.comparing(
                p -> GemiddeldeAankoopPrijs.getGemiddeldeAankoopPrijs(p, date)
        )).toList();
    }
}