package be.kdg.hifresh.businessLayer.domain.recepten;

import be.kdg.hifresh.businessLayer.domain.util.Label;
import be.kdg.hifresh.businessLayer.domain.verkoop.Maaltijd;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class represents a recipe for preparing a dish.
 * It extends the Bereiding class and contains a list of preparation steps and sub-recipes.
 * It also contains a meal, a list of labels, a preparation time, and a photo associated with the recipe.
 */
public class Recept extends Bereiding {

    /**
     * List of preparation steps and sub-recipes associated with this recipe.
     */
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

    /**
     * Constructor for creating a new recipe.
     *
     * @param name         The name of the recipe.
     * @param id           The id of the recipe.
     * @param beschrijving The description of the recipe.
     */
    Recept(String name, int id, String beschrijving) {
        super(id, name, beschrijving);
        this.BEREIDINGEN = new ArrayList<>();
    }

    /**
     * Retrieves all sub-recipes associated with this recipe.
     *
     * @return List of sub-recipes.
     */
    public List<Recept> getSubrecepts() {
        return BEREIDINGEN.stream()
                .filter(bereiding -> bereiding instanceof Recept)
                .map(bereiding -> (Recept) bereiding)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all preparation steps associated with this recipe.
     *
     * @return List of preparation steps.
     */
    public List<BereidingsStap> getStappen() {
        return Stream.concat(
                        BEREIDINGEN.stream()
                                .filter(BereidingsStap.class::isInstance)
                                .map(BereidingsStap.class::cast),
                        getSubrecepts().stream()
                                .flatMap(subRecept -> subRecept.getStappen().stream())
                )
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a preparation step by its sequence number.
     *
     * @param volgnummer Sequence number of the preparation step.
     * @return Preparation step.
     */
    public BereidingsStap getStap(int volgnummer){
        return getStappen().stream()
                .filter(stap -> stap.getVolgNummer() == volgnummer)
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves all ingredients associated with this recipe.
     *
     * @return List of ingredients.
     */
    public List<Ingredient> getIngredients(){
        return this.getStappen().stream()
                .flatMap(stap -> stap.getINGREDIENTS().stream())
                .collect(Collectors.toList());
    }

    /**
     * Adds a sub-recipe to the list of sub-recipes associated with this recipe.
     *
     * @param recept The sub-recipe to be added.
     * @param stap The sequence number of the preparation step.
     * @return true if the sub-recipe was added successfully, false otherwise.
     */
    public boolean addSubrecept(Recept recept, int stap) {
        List<BereidingsStap> subStappen = recept.getStappen();
        this.getStappen().stream()
                .filter(s -> s.getVolgNummer() > stap)
                .forEach(s -> s.setVolgNummer(s.getVolgNummer() + subStappen.size()));
        subStappen.forEach(s -> s.setVolgNummer(s.getVolgNummer() + stap));

        return this.BEREIDINGEN.add(recept);
    }

    /**
     * Adds an ingredient to a preparation step of this recipe.
     *
     * @param ingredient The ingredient to be added.
     * @param volgnummer The sequence number of the preparation step.
     */
    public void addIngredient(Ingredient ingredient, int volgnummer) {
        this.getStap(volgnummer).addIngredient(ingredient);
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
                .mapToInt(BereidingsStap::getVolgNummer)
                .max()
                .orElse(0) + 1;
    }
}