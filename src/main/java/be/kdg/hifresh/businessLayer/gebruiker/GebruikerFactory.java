package be.kdg.hifresh.businessLayer.gebruiker;

public final class GebruikerFactory {
    private GebruikerFactory() {
    }

    public static Leverancier createLeverancier(int id, String name) {
        return new Leverancier(id, name);
    }
}
