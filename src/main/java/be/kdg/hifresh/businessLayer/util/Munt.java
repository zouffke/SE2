package be.kdg.hifresh.businessLayer.util;

import lombok.Getter;

public class Munt implements Comparable<Munt>{

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

    @Override
    public int compareTo(Munt o) {
        return Double.compare(this.bedrag, o.bedrag);
    }
}