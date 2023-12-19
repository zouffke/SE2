package be.kdg.hifresh.businessLayer.domain.aankoop;

import be.kdg.hifresh.businessLayer.domain.util.Adres;
import be.kdg.hifresh.businessLayer.domain.verzending.MaaltijdBox;
import lombok.Getter;

import java.util.List;

/**
 * Represents a distribution center where FreshBox meals are assembled.
 */
public class DistributieCentrum {

    //region vars
    /**
     * The id of this distribution center.
     */
    @Getter
    private final int ID;
    /**
     * The name of this distribution center.
     */
    private final String NAME;
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

    //endregion

    //region constructors

    /**
     * Constructor for DistributieCentrum.
     *
     * @param id   The id of the distribution center.
     * @param name The name of the distribution center.
     */
    DistributieCentrum(int id, String name) {
        this.ID = id;
        this.NAME = name;
    }

    //endregion
}