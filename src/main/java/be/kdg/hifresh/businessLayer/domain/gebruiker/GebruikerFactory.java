package be.kdg.hifresh.businessLayer.domain.gebruiker;

public final class GebruikerFactory {
    private GebruikerFactory() {
    }

    public static Leverancier createLeverancier(int id, String name) {
        return new Leverancier(id, name);
    }
}
