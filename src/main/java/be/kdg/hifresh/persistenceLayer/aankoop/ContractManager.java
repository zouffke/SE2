package be.kdg.hifresh.persistenceLayer.aankoop;

import be.kdg.hifresh.persistenceLayer.Manager;
import be.kdg.hifresh.persistenceLayer.aankoop.catalogs.ContractCataloog;
import be.kdg.hifresh.persistenceLayer.aankoop.catalogs.DistributieCentraCataloog;
import be.kdg.hifresh.businessLayer.aankoop.Contract;
import be.kdg.hifresh.businessLayer.aankoop.Product;
import be.kdg.hifresh.businessLayer.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.util.Munt;
import be.kdg.hifresh.businessLayer.util.PrijsAfspraak;
import be.kdg.hifresh.businessLayer.util.UtilFactory;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A manager class for handling contracts.
 * Extends the Manager class.
 * It provides methods to get average purchase price, get active products, get average weekly and yearly purchase price,
 * get percentage difference, sort products based on score and get product suggestions.
 *
 * @author Dandois Luca
 */
@Getter
public class ContractManager extends Manager {

    //region vars
    /**
     * Catalog of contracts.
     */
    private final ContractCataloog contractCataloog;

    /**
     * Catalog of distribution centers.
     */
    private final DistributieCentraCataloog dcCataloog;
    //endregion

    //region constructors

    /**
     * Constructor for ContractManager.
     * Initializes the contract catalog and distribution center catalog.
     *
     * @author Dandois Luca
     */
    public ContractManager() {
        this.contractCataloog = new ContractCataloog();
        this.dcCataloog = new DistributieCentraCataloog();
    }
    //endregion

    /**
     * Calculates the average purchase price for a list of ingredients on a given date.
     *
     * @param ingredients List of ingredients.
     * @param date The date for which to calculate the average purchase price.
     * @return The average purchase price.
     */
    public Munt getGemiddeldeAankoopPrijs(List<Ingredient> ingredients, LocalDate date) {
        double totaalBedrag = 0;
        double totaalHoeveelheid = 0;

        for (Ingredient ingredient : ingredients) {
            totaalBedrag += this.getGemiddeldeAankoopPrijs(ingredient.getProduct(), date);
            totaalHoeveelheid += ingredient.getHoeveelheid();
        }

        return UtilFactory.createMunt(totaalBedrag * totaalHoeveelheid, "Euro");
    }

    /**
     * Calculates the average purchase price for a product on a given date.
     *
     * @param product The product for which to calculate the average purchase price.
     * @param date The date for which to calculate the average purchase price.
     * @return The average purchase price.
     */
    private double getGemiddeldeAankoopPrijs(Product product, LocalDate date) {
        double bedrag = 0;
        double hoeveelheid = 0;

        for (Contract contract : product.getContracten()) {
            for (PrijsAfspraak prijsAfspraak : contract.getGeldendePrijsAfspraken(date)) {
                double tempHoeveelheid = prijsAfspraak.getMaxHoeveelheid();
                bedrag += prijsAfspraak.getPrijs().getBedrag() * tempHoeveelheid;
                hoeveelheid += tempHoeveelheid;
            }
        }
        bedrag /= hoeveelheid;

        return bedrag;
    }

    /**
     * Retrieves a list of active products on a given date.
     *
     * @param date The date for which to retrieve the active products.
     * @return A list of active products.
     */
    private List<Product> getActiveProducts(LocalDate date) {
        return contractCataloog.getList()
                .stream()
                .filter(contract -> contract.getClausules() != null && contract.getClausules().stream().anyMatch(clausule -> clausule.isActive(date)))
                .map(Contract::getProduct)
                .toList();
    }

    /**
     * Calculates the average weekly purchase price for a product on a given date.
     *
     * @param date The date for which to calculate the average weekly purchase price.
     * @param product The product for which to calculate the average weekly purchase price.
     * @return The average weekly purchase price.
     */
    private Munt getGemiddeldeWeekAankoopPrijs(LocalDate date, Product product) {
        double bedrag = 0;
        double days = 0;

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            LocalDate day = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth())
                    .with(dayOfWeek);

            double dayAmt = this.getGemiddeldeAankoopPrijs(product, day);

            if (dayAmt > 0) {
                bedrag += dayAmt;
                days++;
            }
        }

        if (days > 0) {
            bedrag /= days;
        }

        return UtilFactory.createMunt(bedrag, "Euro");
    }

    /**
     * Calculates the average yearly purchase price for a product on a given date.
     *
     * @param date The date for which to calculate the average yearly purchase price.
     * @param product The product for which to calculate the average yearly purchase price.
     * @return The average yearly purchase price.
     */
    private Munt getGemiddeldeJaarAankoopPrijs(LocalDate date, Product product) {
        double bedrag = 0;
        double weeks = 0;

        LocalDate week = LocalDate.of(date.getYear(), 1, 1);

        while (week.isBefore(LocalDate.of(date.getYear() + 1, 1, 1))) {
            Munt munt = this.getGemiddeldeWeekAankoopPrijs(week, product);

            double tempBedrag = munt.getBedrag();

            if (tempBedrag > 0) {
                bedrag += tempBedrag;
                weeks++;
            }

            week = week.plusDays(7);
        }

        return UtilFactory.createMunt(bedrag / weeks, "Euro");
    }

    /**
     * Calculates the percentage difference between the weekly and yearly average purchase price.
     *
     * @param week The weekly average purchase price.
     * @param year The yearly average purchase price.
     * @return The percentage difference.
     */
    private double getProcentueelVerschil(Munt week, Munt year) {
        return (1 - (week.getBedrag() / year.getBedrag()) * 100);
    }

    /**
     * Sorts a list of products based on their scores in descending order.
     *
     * @param scores A map where the keys are products and the values are their corresponding scores.
     * @return A list of products sorted in descending order of their scores.
     */
    private List<Product> sortOnScore(Map<Product, Double> scores) {
        List<Product> products = new ArrayList<>(scores.keySet());

        products.sort((product1, product2) -> scores.get(product2).compareTo(scores.get(product1)));

        return products;
    }

    /**
     * Provides product suggestions for a given date.
     *
     * @param date The date for which to provide product suggestions.
     * @return A list of product suggestions.
     */
    public List<Product> getProductSuggesties(LocalDate date) {
        List<Product> products = getActiveProducts(date);
        Map<Product, Munt> weekAvg = new HashMap<>();
        Map<Product, Munt> yearAvg = new HashMap<>();
        Map<Product, Double> score = new HashMap<>();

        for (Product product : products) {
            weekAvg.put(product, getGemiddeldeWeekAankoopPrijs(date, product));
            yearAvg.put(product, getGemiddeldeJaarAankoopPrijs(date, product));

            score.put(product, this.getProcentueelVerschil(weekAvg.get(product), yearAvg.get(product)));
        }

        return this.sortOnScore(score);
    }
}