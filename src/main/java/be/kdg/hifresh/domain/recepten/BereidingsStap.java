package be.kdg.hifresh.domain.recepten;

/**
 * Represents a step in the preparation of a recipe.
 */
public class BereidingsStap {
    //region vars

    /**
     * The name of this preparation step.
     */
    private String naam;

    /**
     * The description of this preparation step.
     */
    private String beschrijving;

    /**
     * The id of this preparation step.
     */
    private final int id;
    //endregion
    /**
     * Constructor for BereidingsStap.
     *
     * @param naam The name of the preparation step.
     * @param beschrijving The description of the preparation step.
     * @param id The id of the preparation step.
     */
    BereidingsStap(String naam, String beschrijving, int id){
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.id = id;
    }
}