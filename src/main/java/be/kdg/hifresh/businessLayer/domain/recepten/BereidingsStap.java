package be.kdg.hifresh.businessLayer.domain.recepten;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a step in the preparation of a recipe.
 */
public class BereidingsStap extends Bereiding {
    //region vars
    private final List<Integer> INGREDIENT_IDS;

    @Getter
    @Setter
    private int volgNummer;
    //endregion

    /**
     * Constructor for BereidingsStap.
     *
     * @param name         The name of the preparation step.
     * @param beschrijving The description of the preparation step.
     * @param id           The id of the preparation step.
     */
    BereidingsStap(String name, String beschrijving, int id, int volgNummer) {
        super(id, name, beschrijving);
        this.INGREDIENT_IDS = new ArrayList<>();
        this.volgNummer = volgNummer;
    }

    public boolean addIngredient(int id) {
        return this.INGREDIENT_IDS.add(id);
    }
}