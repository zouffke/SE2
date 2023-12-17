package be.kdg.hifresh.businessLayer.recepten;

import be.kdg.hifresh.businessLayer.verkoop.*;

import java.time.Duration;
import java.util.*;
import be.kdg.hifresh.businessLayer.util.*;
import lombok.Getter;

/**
 * Represents a recipe for preparing a dish.
 */
public class Recept {

    //region vars
    /**
     * The meal associated with this recipe.
     */
    private Maaltijd maaltijd;

    /**
     * The list of sub-recipes associated with this recipe.
     */
    @Getter
    private List<Recept> subrecepten;

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
     * The list of preparation steps for this recipe.
     */
    @Getter
    private final List<BereidingsStap> stappen;

    /**
     * The name of this recipe.
     */
    private String naam;

    /**
     * The time required to prepare this recipe.
     */
    private Duration bereidingstijd;

    /**
     * The photo of this recipe.
     */
    private String foto;

    /**
     * The id of this recipe.
     */
    @Getter
    private final int id;

    /**
     * The description of this recipe.
     */
    private String beschrijving;
    //endregion

    //region constructors
    /**
     * Constructor for Recept.
     *
     * @param naam The name of the recipe.
     * @param id The id of the recipe.
     * @param beschrijving The description of the recipe.
     */
    Recept(String naam, int id, String beschrijving){
        this.naam = naam;
        this.id = id;
        this.beschrijving = beschrijving;
        this.stappen = new ArrayList<>();
    }
    //endregion

    /**
     * Adds a sub-recipe to the list of sub-recipes associated with this recipe.
     *
     * @param recept The sub-recipe to be added.
     * @return true if the sub-recipe was added successfully, false otherwise.
     */
    public boolean addSubrecept(Recept recept){
        if (subrecepten == null) {
            subrecepten = new ArrayList<>();
        }

        return subrecepten.add(recept);
    }

    /**
     * Adds an ingredient to the list of ingredients needed for this recipe.
     *
     * @param ingredient The ingredient to be added.
     * @return true if the ingredient was added successfully, false otherwise.
     */
    public boolean addIngredient(Ingredient ingredient){
        if (this.ingredienten == null){
            this.ingredienten = new ArrayList<>();
        }

        return this.ingredienten.add(ingredient);
    }

    /**
     * Adds a preparation step to the list of preparation steps for this recipe at the specified position.
     *
     * @param stap The preparation step to be added.
     * @param volgnummer The position at which the preparation step will be added.
     */
    public void addBereidingsStap(BereidingsStap stap, int volgnummer){
        this.stappen.add(volgnummer - 1, stap);
    }

    /**
     * Adds a preparation step to the list of preparation steps for this recipe at the end of the list.
     *
     * @param stap The preparation step to be added.
     */
    public void addBereidingsStap(BereidingsStap stap){
        this.addBereidingsStap(stap, getNextVolgnummer());
    }

    /**
     * Returns the next position at which a preparation step can be added to the list of preparation steps for this recipe.
     *
     * @return The next position at which a preparation step can be added.
     */
    public int getNextVolgnummer(){
        return this.stappen.size() + 1;
    }

    public BereidingsStap getBereidingStap(int volnummer){
        return this.stappen.get(volnummer - 1);
    }
}