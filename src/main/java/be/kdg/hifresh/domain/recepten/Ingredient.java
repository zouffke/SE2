package be.kdg.hifresh.domain.recepten;

import be.kdg.hifresh.domain.aankoop.Product;
import be.kdg.hifresh.domain.util.Eenheid;

/**
 * Product dat nodig is voor het bereiden van een gerecht.
 */
public class Ingredient {

    private final Product product;
    private double hoeveelheid;
    private Eenheid eenheid;
    private final int id;

    Ingredient(int id, Product product, double hoeveelheid) {
        this.id = id;
        this.product = product;
        this.hoeveelheid = hoeveelheid;
    }

    int getId() {
        return this.id;
    }
}