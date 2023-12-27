package be.kdg.hifresh.businessLayer.services;

import be.kdg.hifresh.persistenceLayer.Catalog;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that provides a base for all manager classes.
 * Implements the IManager interface.
 */
public abstract class Manager {

    //region vars

    /**
     * List of catalogs managed by this manager.
     */
    private final List<Catalog<?>> catalogs;

    //endregion

    //region constructors

    /**
     * Constructor for Manager.
     * Initializes the list of catalogs.
     */
    protected Manager() {
        this.catalogs = new ArrayList<>();
    }

    //endregion

    //region inner manager functions

    /**
     * Adds a catalog to the list of catalogs managed by this manager.
     *
     * @param catalog The catalog to be added.
     */
    protected void addCatalog(Catalog<?> catalog) {
        catalogs.add(catalog);
    }

    /**
     * Retrieves a catalog of a specific type from the list of catalogs managed by this manager.
     *
     * @param catalogClass The class of the catalog to be retrieved.
     * @param <V> The type of the objects in the catalog.
     * @param <T> The type of the catalog.
     * @return The catalog of the specified type.
     * @throws IllegalArgumentException if the catalog is not found.
     */
    public <V, T extends Catalog<V>> Catalog<V> getCatalog(Class<T> catalogClass) {
        return catalogs.stream()
                .filter(catalogClass::isInstance)
                .findFirst()
                .map(catalogClass::cast)
                .orElseThrow(() -> new IllegalArgumentException("Catalog not found"));
    }

    //endregion

    //region catalog functions

    /**
     * Adds an object to a catalog.
     *
     * @param object  The object to be added to the catalog.
     * @param catalog The catalog to which the object will be added.
     * @param <T>     The type of the object.
     * @return true if the object was added successfully, false otherwise.
     */
    public <T> boolean add(T object, Catalog<T> catalog) {
        return catalog.add(object);
    }

    /**
     * Retrieves an object from a catalog by its index.
     *
     * @param index The index of the object in the catalog.
     * @param catalog The catalog from which the object will be retrieved.
     * @param <T> The type of the object.
     * @return The object at the specified index in the catalog.
     */
    public <T> T get(int index, Catalog<T> catalog) {
        return catalog.get(index);
    }

    /**
     * Retrieves an object from a catalog by its ID.
     *
     * @param objId The ID of the object in the catalog.
     * @param catalog The catalog from which the object will be retrieved.
     * @param <T> The type of the object.
     * @return The object with the specified ID in the catalog.
     */
    public <T> T getById(int objId, Catalog<T> catalog) {
        return catalog.get(catalog.indexById(objId));
    }

    /**
     * Retrieves all objects of a specific type from a catalog.
     *
     * @param id The ID of the catalog.
     * @param object The class of the objects to be retrieved.
     * @param <T> The type of the objects.
     * @return A list of all objects of the specified type in the catalog.
     */
    public <T> List<T> getAllT(int id, Class<T> object) {
        return null;
    }

    /**
     * Clears all catalogs managed by this manager.
     */
    public void clearCatalogs() {
        catalogs.forEach(Catalog::clear);
    }

    /**
     * Retrieves a list of objects from a catalog by their name.
     *
     * @param name The name of the objects to be retrieved.
     * @param object The class of the objects to be retrieved.
     * @param <T> The type of the objects.
     * @return A list of objects with the specified name in the catalog.
     * @throws InvocationTargetException if the underlying method throws an exception.
     * @throws IllegalAccessException if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    public <T> List<T> getTByName(String name, Class<T> object) throws InvocationTargetException, IllegalAccessException {
        return null;
    }
    //endregion
}