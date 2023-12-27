package be.kdg.hifresh.businessLayer.domain.recepten;

import lombok.Getter;

/**
 * This class represents a preparation step in a recipe.
 * It contains an ID, a name, and a description for the preparation step.
 */
@Getter
public class Bereiding {

    /**
     * Unique identifier for the preparation step.
     */
    private final int ID;

    /**
     * Name of the preparation step.
     */
    private final String NAME;

    /**
     * Description of the preparation step.
     */
    private final String BESCHRIJVING;

    /**
     * Constructor for creating a new preparation step.
     *
     * @param id the id of the new preparation step
     * @param name the name of the new preparation step
     * @param beschrijving the description of the new preparation step
     */
    Bereiding(int id, String name, String beschrijving){
        this.ID = id;
        this.NAME = name;
        this.BESCHRIJVING = beschrijving;
    }
}