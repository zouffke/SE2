package be.kdg.hifresh.businessLayer.domain.recepten;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a step in the preparation of a recipe.
 */
public class BereidingsStap {
    //region vars

    /**
     * The name of this preparation step.
     */
    private final String NAME;

    /**
     * The description of this preparation step.
     */
    @Getter
    private final String BESCHRIJVING;

    /**
     * The id of this preparation step.
     */
    private final int ID;
    private final List<Integer> INGREDIENT_IDS;
    //endregion

    /**
     * Constructor for BereidingsStap.
     *
     * @param name         The name of the preparation step.
     * @param beschrijving The description of the preparation step.
     * @param id           The id of the preparation step.
     */
    BereidingsStap(String name, String beschrijving, int id) {
        this.NAME = name;
        this.BESCHRIJVING = beschrijving;
        this.ID = id;
        this.INGREDIENT_IDS = new ArrayList<>();
    }

    public boolean addIngredient(int id) {
        return this.INGREDIENT_IDS.add(id);
    }
}