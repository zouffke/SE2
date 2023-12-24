package be.kdg.hifresh.businessLayer.services.aankoop;

import be.kdg.hifresh.businessLayer.domain.aankoop.Contract;
import be.kdg.hifresh.businessLayer.domain.aankoop.DistributieCentrum;
import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import be.kdg.hifresh.businessLayer.domain.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.domain.util.Munt;
import be.kdg.hifresh.businessLayer.services.aankoop.productSuggestions.IProductSuggestionsStrat;
import be.kdg.hifresh.persistenceLayer.Catalog;
import be.kdg.hifresh.businessLayer.services.Manager;
import be.kdg.hifresh.persistenceLayer.aankoop.ContractCataloog;
import be.kdg.hifresh.persistenceLayer.aankoop.DistributieCentraCataloog;
import be.kdg.hifresh.persistenceLayer.aankoop.ProductCataloog;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.*;

/**
 * A manager class for handling contracts.
 * Extends the Manager class.
 * It provides methods to get average purchase price, get active products, get average weekly and yearly purchase price,
 * get percentage difference, sort products based on score and get product suggestions.
 *
 * @author Dandois Luca
 */
@Getter
@Service
public class AankoopManager extends Manager {

    //region vars
    /**
     * Catalog of contracts.
     */
    private final Catalog<Contract> CONTRACT_CATALOG;

    /**
     * Catalog of distribution centers.
     */
    private final Catalog<DistributieCentrum> DC_CATALOG;
    private final Catalog<Product> PRODUCT_CATALOG;
    //endregion

    //region constructors

    /**
     * Constructor for ContractManager.
     * Initializes the contract catalog and distribution center catalog.
     *
     * @author Dandois Luca
     */
    @Autowired
    public AankoopManager(
            ContractCataloog contractCataloog,
            DistributieCentraCataloog distributieCentraCataloog,
            ProductCataloog productCataloog) {
        this.CONTRACT_CATALOG = contractCataloog;
        this.DC_CATALOG = distributieCentraCataloog;
        this.PRODUCT_CATALOG = productCataloog;
    }
    //endregion

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
        return CONTRACT_CATALOG.getList()
                .stream()
                .filter(contract -> contract.getCLAUSULES() != null && contract.getCLAUSULES().stream().anyMatch(clausule -> clausule.isActive(date)))
                .map(Contract::getPRODUCT)
                .distinct()
                .toList();
    }

    public List<Product> getProductsByName(String name) throws InvocationTargetException, IllegalAccessException {
        return this.PRODUCT_CATALOG.getByName(name);
    }

    public List<Product> sortOnAvgPrice(List<Product> list, LocalDate date) {
        return list.stream().sorted(Comparator.comparing(
                p -> GemiddeldeAankoopPrijs.getGemiddeldeAankoopPrijs(p, date)
        )).toList();
    }
}