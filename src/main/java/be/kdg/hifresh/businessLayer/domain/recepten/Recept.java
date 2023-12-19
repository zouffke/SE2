package be.kdg.hifresh.businessLayer.domain.recepten;

import be.kdg.hifresh.businessLayer.domain.util.Label;
import be.kdg.hifresh.businessLayer.domain.verkoop.Maaltijd;
import lombok.Getter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a recipe for preparing a dish.
 */
public class Recept extends Bereiding {

    //region vars
    private final List<Bereiding> BEREIDINGEN;
    /**
     * The meal associated with this recipe.
     */
    private Maaltijd maaltijd;
    /**
     * The list of labels associated with this recipe.
     */
    private List<Label> labels;
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
        super(id, name, beschrijving);
        this.BEREIDINGEN = new ArrayList<>();
    }
    //endregion

    public List<Recept> getSubrecepts() {
        return BEREIDINGEN.stream()
                .filter(bereiding -> bereiding instanceof Recept)
                .map(bereiding -> (Recept) bereiding)
                .collect(Collectors.toList());
    }

    public List<BereidingsStap> getStappen() {
        return BEREIDINGEN.stream()
                .filter(bereiding -> bereiding instanceof BereidingsStap)
                .map(bereiding -> (BereidingsStap) bereiding)
                .collect(Collectors.toList());
    }

    /**
     * Adds a sub-recipe to the list of sub-recipes associated with this recipe.
     *
     * @param recept The sub-recipe to be added.
     * @return true if the sub-recipe was added successfully, false otherwise.
     */
    //TODO
    public boolean addSubrecept(Recept recept, int stap) {
        List<BereidingsStap> subStappen = recept.getStappen();
        this.getStappen().stream()
                .filter(s -> s.getVolgNummer() > stap)
                .forEach(s -> s.setVolgNummer(s.getVolgNummer() + subStappen.size()));
        subStappen.forEach(s -> s.setVolgNummer(s.getVolgNummer() + stap));

        return this.BEREIDINGEN.add(recept);
    }

    /**
     * Adds an ingredient to the list of ingredients needed for this recipe.
     *
     * @param ingredient The ingredient to be added.
     * @return true if the ingredient was added successfully, false otherwise.
     */
    public boolean addIngredient(Ingredient ingredient) {
        return this.INGREDIENTEN.add(ingredient);
    }

    /**
     * Adds a preparation step to the list of preparation steps for this recipe at the end of the list.
     *
     * @param stap The preparation step to be added.
     */
    public void addBereidingsStap(BereidingsStap stap) {
        this.BEREIDINGEN.add(stap);
    }

    /**
     * Returns the next position at which a preparation step can be added to the list of preparation steps for this recipe.
     *
     * @return The next position at which a preparation step can be added.
     */
    public int getNextVolgnummer() {
        return this.getStappen().stream()
                .map(BereidingsStap::getVolgNummer)
                .max(Comparator.naturalOrder())
                .orElse(1);
    }

    public BereidingsStap getBereidingStap(int volgnummer) {
        return this.getStappen().stream()
                .filter(s -> s.getVolgNummer() == volgnummer)
                .findFirst()
                .orElse(null);
    }
}