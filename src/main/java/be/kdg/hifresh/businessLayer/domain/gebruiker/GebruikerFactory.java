package be.kdg.hifresh.businessLayer.domain.gebruiker;

/**
 * This class is a factory for creating instances of the Leverancier class.
 * It provides a method to create a new Leverancier.
 */
public final class GebruikerFactory {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private GebruikerFactory() {
    }

    /**
     * Creates a new Leverancier with the given id and name.
     *
     * @param id   the id of the new Leverancier
     * @param name the name of the new Leverancier
     * @return a new Leverancier with the given id and name
     */
    public static Leverancier createLeverancier(int id, String name) {
        return new Leverancier(id, name);
    }
}