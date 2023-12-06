package be.kdg.hifresh.domain.aankoop;

import java.util.*;
import be.kdg.hifresh.domain.verzending.*;
import be.kdg.hifresh.domain.util.*;

/**
 * Represents a distribution center where FreshBox meals are assembled.
 */
public class DistributieCentrum {

    //region vars
    /**
     * The list of deliveries to this distribution center.
     */
    private List<Levering> leveringen;

    /**
     * The list of meal boxes assembled at this distribution center.
     */
    private List<MaaltijdBox> maaltijdBoxen;

    /**
     * The address of this distribution center.
     */
    private Adres adres;

    /**
     * The id of this distribution center.
     */
    private final int id;

    /**
     * The name of this distribution center.
     */
    private String name;

    //endregion

    //region constructors
    /**
     * Constructor for DistributieCentrum.
     *
     * @param id The id of the distribution center.
     * @param name The name of the distribution center.
     */
    DistributieCentrum(int id, String name){
        this.id = id;
        this.name = name;
    }

    //endregion
}