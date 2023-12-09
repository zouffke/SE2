package be.kdg.hifresh.dal.aankoop;

import be.kdg.hifresh.dal.Manager;
import be.kdg.hifresh.dal.aankoop.catalogs.ContractCataloog;
import be.kdg.hifresh.dal.aankoop.catalogs.DistributieCentraCataloog;
import be.kdg.hifresh.domain.aankoop.Contract;
import be.kdg.hifresh.domain.aankoop.Product;
import be.kdg.hifresh.domain.recepten.Ingredient;
import be.kdg.hifresh.domain.util.Munt;
import be.kdg.hifresh.domain.util.PrijsAfspraak;
import be.kdg.hifresh.domain.util.UtilFactory;
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

    public Munt getGemiddeldeAankoopPrijs(List<Ingredient> ingredients, LocalDate date) {
        double totaalBedrag = 0;
        double totaalHoeveelheid = 0;

        for (Ingredient ingredient : ingredients) {
            totaalBedrag += this.getGemiddeldeAankoopPrijs(ingredient.getProduct(), date);
            totaalHoeveelheid += ingredient.getHoeveelheid();
        }

        return UtilFactory.createMunt(totaalBedrag * totaalHoeveelheid, "Euro");
    }

    private double getGemiddeldeAankoopPrijs(Product product, LocalDate date) {
        double bedrag = 0;
        double hoeveelheid = 0;

        for (Contract contract : product.getContracten()) {
            for (PrijsAfspraak prijsAfspraak : contract.getGeldendePrijsAfspraken(date)) {
                bedrag += prijsAfspraak.getPrijs().getBedrag() * prijsAfspraak.getMaxHoeveelheid();
                hoeveelheid += prijsAfspraak.getMaxHoeveelheid();
            }
        }
        bedrag /= hoeveelheid;

        return bedrag;
    }

    private List<Product> getActiveProducts(LocalDate date) {
        return contractCataloog.getList()
                .stream()
                .filter(contract -> contract.getClausules() != null && contract.getClausules().stream().anyMatch(clausule -> clausule.isActive(date)))
                .map(Contract::getProduct)
                .toList();
    }

    private Munt getGemiddeldeWeekAankoopPrijs(LocalDate date, Product product) {
        double bedrag = 0;
        double days = 0;

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            LocalDate day = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth())
                    .with(dayOfWeek);

            double dayAmt = this.getGemiddeldeAankoopPrijs(product, day);

            if (dayAmt > 0) {
                bedrag += this.getGemiddeldeAankoopPrijs(product, day);
                days++;
            }
        }

        if (days > 0) {
            bedrag /= days;
        }

        return UtilFactory.createMunt(bedrag, "Euro");
    }

    private Munt getGemiddeldeJaarAankoopPrijs(LocalDate date, Product product) {
        double bedrag = 0;
        double weeks = 0;

        LocalDate week = LocalDate.of(date.getYear(), 1, 1);

        while (week.isBefore(LocalDate.of(date.getYear() + 1, 1, 1))) {
            Munt munt = this.getGemiddeldeWeekAankoopPrijs(week, product);

            if (munt.getBedrag() > 0) {
                bedrag += munt.getBedrag();
                weeks++;
            }

            week = week.plusDays(7);
        }

        return UtilFactory.createMunt(bedrag / weeks, "Euro");
    }

    private double getProcentueelVerschil(Munt week, Munt year) {
        return (1 - (week.getBedrag() / year.getBedrag()) * 100);
    }

    private List<Product> sortOnScore(Map<Product, Double> scores) {
        List<Product> products = new ArrayList<>(scores.keySet());

        products.sort((product1, product2) -> scores.get(product2).compareTo(scores.get(product1)));

        return products;
    }

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