package be.kdg.hifresh.domain.recepten;

public class BereidingsStap {
    private String naam;
    private String beschrijving;
    private final int id;

    BereidingsStap(String naam, String beschrijving, int id){
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.id = id;
    }
}