package be.kdg.hifresh.businessLayer.services;

import be.kdg.hifresh.persistenceLayer.Catalog;

/**
 * An interface for managing catalogs of objects.
 */
public interface IManager {
    //region catalog functions

    /**
     * Adds an object to a catalog.
     *
     * @param object  The object to be added to the catalog.
     * @param catalog The catalog to which the object will be added.
     * @param <T>     The type of the object.
     * @return true if the object was added successfully, false otherwise.
     */
    @SuppressWarnings("unused")
    <T> boolean add(T object, Catalog<T> catalog);

    /**
     * Retrieves an object from a catalog at the specified index.
     *
     * @param index   The index of the object to be retrieved.
     * @param catalog The catalog from which the object will be retrieved.
     * @param <T>     The type of the object.
     * @return The object at the specified index.
     */
    @SuppressWarnings("unused")
    <T> T get(int index, Catalog<T> catalog);

    /**
     * Retrieves an object from a catalog by its ID.
     *
     * @param objId   The ID of the object to be retrieved.
     * @param catalog The catalog from which the object will be retrieved.
     * @param <T>     The type of the object.
     * @return The object with the specified ID.
     */
    @SuppressWarnings("unused")
    <T> T getById(int objId, Catalog<T> catalog);

    //endregion

}