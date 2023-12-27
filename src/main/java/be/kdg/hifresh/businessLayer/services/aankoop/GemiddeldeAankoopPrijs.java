package be.kdg.hifresh.businessLayer.services.aankoop;

import be.kdg.hifresh.businessLayer.domain.aankoop.Contract;
import be.kdg.hifresh.businessLayer.domain.aankoop.Product;
import be.kdg.hifresh.businessLayer.domain.recepten.Ingredient;
import be.kdg.hifresh.businessLayer.domain.util.Munt;
import be.kdg.hifresh.businessLayer.domain.util.PrijsAfspraak;
import be.kdg.hifresh.businessLayer.domain.util.UtilFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * This class provides utility methods for calculating the average purchase price of products.
 * It contains methods to calculate the average purchase price for a list of ingredients, a single product,
 * and weekly and yearly averages for a product.
 */
public final class GemiddeldeAankoopPrijs {
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private GemiddeldeAankoopPrijs(){}

    /**
     * Calculates the average purchase price for a list of ingredients on a given date.
     *
     * @param ingredients List of ingredients for which the average purchase price is to be calculated.
     * @param date The date for which the average purchase price is to be calculated.
     * @return The average purchase price as a Munt object.
     */
    static Munt getGemiddeldeAankoopPrijs(List<Ingredient> ingredients, LocalDate date){
        double totaalBedrag = 0;

        for (Ingredient ingredient : ingredients) {
            totaalBedrag += getGemiddeldeAankoopPrijs(ingredient.getPRODUCT(), date) * ingredient.getHOEVEELHEID();
        }

        return UtilFactory.createMunt(totaalBedrag, "Euro");
    }

    /**
     * Calculates the average purchase price for a product on a given date.
     *
     * @param product The product for which to calculate the average purchase price.
     * @param date    The date for which to calculate the average purchase price.
     * @return The average purchase price.
     */
    static double getGemiddeldeAankoopPrijs(Product product, LocalDate date) {
        double bedrag = 0;
        double hoeveelheid = 0;

        for (Contract contract : product.getCONTRACTEN()) {
            for (PrijsAfspraak prijsAfspraak : contract.getGeldendePrijsAfspraken(date)) {
                double tempHoeveelheid = prijsAfspraak.getMAX_HOEVEELHEID();
                bedrag += prijsAfspraak.getPRIJS().getBEDRAG() * tempHoeveelheid;
                hoeveelheid += tempHoeveelheid;
            }
        }
        bedrag /= hoeveelheid;

        return bedrag;
    }

    /**
     * Calculates the average weekly purchase price for a product on a given date.
     *
     * @param date    The date for which to calculate the average weekly purchase price.
     * @param product The product for which to calculate the average weekly purchase price.
     * @return The average weekly purchase price.
     */
   public static Munt getGemiddeldeWeekAankoopPrijs(LocalDate date, Product product) {
        double bedrag = 0;
        double days = 0;

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            LocalDate day = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth())
                    .with(dayOfWeek);

            double dayAmt = getGemiddeldeAankoopPrijs(product, day);

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
     * @param date    The date for which to calculate the average yearly purchase price.
     * @param product The product for which to calculate the average yearly purchase price.
     * @return The average yearly purchase price.
     */
    public static Munt getGemiddeldeJaarAankoopPrijs(LocalDate date, Product product) {
        double bedrag = 0;
        double weeks = 0;

        LocalDate week = LocalDate.of(date.getYear(), 1, 1);

        while (week.isBefore(LocalDate.of(date.getYear() + 1, 1, 1))) {
            Munt munt = getGemiddeldeWeekAankoopPrijs(week, product);

            double tempBedrag = munt.getBEDRAG();

            if (tempBedrag > 0) {
                bedrag += tempBedrag;
                weeks++;
            }

            week = week.plusDays(7);
        }

        return UtilFactory.createMunt(bedrag / weeks, "Euro");
    }
}