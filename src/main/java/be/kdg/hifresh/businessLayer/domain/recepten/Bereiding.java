package be.kdg.hifresh.businessLayer.domain.recepten;

import lombok.Getter;

public class Bereiding {

    @Getter
    private final int ID;
    @Getter
    private final String NAME;
    @Getter
    private final String BESCHRIJVING;

    Bereiding(int id, String name, String beschrijving){
        this.ID = id;
        this.NAME = name;
        this.BESCHRIJVING = beschrijving;
    }
}
