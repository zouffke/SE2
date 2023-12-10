package be.kdg.hifresh.domain.util;

import lombok.Getter;

public class Munt {

    @Getter
    private double bedrag;
    private String eenheid;

    Munt(double bedrag, String eenheid) {
        this.bedrag = bedrag;
        this.eenheid = eenheid;
    }

    public void addBedrag(double bedrag) {
        this.bedrag += bedrag;
    }
}