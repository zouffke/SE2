package be.kdg.hifresh.businessLayer.domain.recepten;

import lombok.Getter;

@Getter
public class Bereiding {

    private final int ID;
    private final String NAME;
    private final String BESCHRIJVING;

    Bereiding(int id, String name, String beschrijving){
        this.ID = id;
        this.NAME = name;
        this.BESCHRIJVING = beschrijving;
    }
}
