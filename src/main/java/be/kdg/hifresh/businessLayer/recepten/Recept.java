package be.kdg.hifresh.businessLayer.recepten;

import be.kdg.hifresh.businessLayer.util.Label;
import be.kdg.hifresh.businessLayer.verkoop.Maaltijd;
import lombok.Getter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a recipe for preparing a dish.
 */
public class Recept {

    //region vars
    /**
     * The list of sub-recipes associated with this recipe.
     */
    @Getter
    private final List<Recept> SUB_RECEPTEN;
    @Getter
    private final List<Recept> SUPER_RECEPTEN;
    /**
     * The list of preparation steps for this recipe.
     */
    @Getter
    private final List<BereidingsStap> STAPPEN;
    /**
     * The name of this recipe.
     */
    private final String NAME;
    /**
     * The id of this recipe.
     */
    @Getter
    private final int ID;
    /**
     * The description of this recipe.
     */
    private final String BESCHRIJVING;
    /**
     * The meal associated with this recipe.
     */
    private Maaltijd maaltijd;
    /**
     * The list of ingredients needed for this recipe.
     */
    @Getter
    private List<Ingredient> ingredienten;
    /**
     * The list of labels associated with this recipe.
     */
    private List<Label> labels;
    /**
     * The main recipe associated with this recipe.
     */
    private Recept hoofdrecept;
    /**
     * The time required to prepare this recipe.
     */
    private Duration bereidingstijd;
    /**
     * The photo of this recipe.
     */
    private String foto;
    //endregion

    //region constructors

    /**
     * Constructor for Recept.
     *
     * @param name         The name of the recipe.
     * @param id           The id of the recipe.
     * @param beschrijving The description of the recipe.
     */
    Recept(String name, int id, String beschrijving) {
        this.NAME = name;
        this.ID = id;
        this.BESCHRIJVING = beschrijving;
        this.STAPPEN = new ArrayList<>();
        SUB_RECEPTEN = new ArrayList<>();
        this.SUPER_RECEPTEN = new ArrayList<>();
    }
    //endregion

    /**
     * Adds a sub-recipe to the list of sub-recipes associated with this recipe.
     *
     * @param recept The sub-recipe to be added.
     * @return true if the sub-recipe was added successfully, false otherwise.
     */
    public boolean addSubrecept(Recept recept, int stap) {
        List<BereidingsStap> subStappen = recept.getSTAPPEN();
        if (!subStappen.isEmpty()) {
            for (int i = 0; i < subStappen.size(); i++) {
                this.STAPPEN.add(stap + i, subStappen.get(i));
            }
        }
        recept.addSuperRecept(this);
        return SUB_RECEPTEN.add(recept);
    }

    private void addSuperRecept(Recept recept) {
        this.SUPER_RECEPTEN.add(recept);
    }

    /**
     * Adds an ingredient to the list of ingredients needed for this recipe.
     *
     * @param ingredient The ingredient to be added.
     * @return true if the ingredient was added successfully, false otherwise.
     */
    public boolean addIngredient(Ingredient ingredient) {
        if (this.ingredienten == null) {
            this.ingredienten = new ArrayList<>();
        }

        return this.ingredienten.add(ingredient);
    }

    /**
     * Adds a preparation step to the list of preparation steps for this recipe at the end of the list.
     *
     * @param stap The preparation step to be added.
     */
    public void addBereidingsStap(BereidingsStap stap) {
        this.addBereidingsStap(stap, getNextVolgnummer());
    }

    /**
     * Adds a preparation step to the list of preparation steps for this recipe at the specified position.
     *
     * @param stap       The preparation step to be added.
     * @param volgnummer The position at which the preparation step will be added.
     */
    public void addBereidingsStap(BereidingsStap stap, int volgnummer) {
        this.STAPPEN.add(volgnummer - 1, stap);
    }

    /**
     * Returns the next position at which a preparation step can be added to the list of preparation steps for this recipe.
     *
     * @return The next position at which a preparation step can be added.
     */
    public int getNextVolgnummer() {
        return this.STAPPEN.size() + 1;
    }

    public BereidingsStap getBereidingStap(int volnummer) {
        return this.STAPPEN.get(volnummer - 1);
    }
}