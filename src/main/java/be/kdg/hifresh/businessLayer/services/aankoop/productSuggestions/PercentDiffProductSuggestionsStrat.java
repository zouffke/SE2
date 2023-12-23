package be.kdg.hifresh.businessLayer.services.aankoop.productSuggestions;

import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import be.kdg.hifresh.businessLayer.domain.util.Munt;
import be.kdg.hifresh.businessLayer.services.aankoop.GemiddeldeAankoopPrijs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentDiffProductSuggestionsStrat implements IProductSuggestionsStrat{
    /**
     * Provides product suggestions for a given date.
     *
     * @param date The date for which to provide product suggestions.
     * @return A list of product suggestions.
     */
    @Override
    public List<Product> getProductSuggesties(LocalDate date, List<Product> products) {
        Map<Product, Munt> weekAvg = new HashMap<>();
        Map<Product, Munt> yearAvg = new HashMap<>();
        Map<Product, Double> score = new HashMap<>();

        for (Product product : products) {
            weekAvg.put(product, GemiddeldeAankoopPrijs.getGemiddeldeWeekAankoopPrijs(date, product));
            yearAvg.put(product, GemiddeldeAankoopPrijs.getGemiddeldeJaarAankoopPrijs(date, product));

            score.put(product, this.getProcentueelVerschil(weekAvg.get(product), yearAvg.get(product)));
        }

        return this.sortOnScore(score);
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
}
