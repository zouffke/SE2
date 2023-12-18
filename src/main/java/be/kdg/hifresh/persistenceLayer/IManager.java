package be.kdg.hifresh.persistenceLayer;

import java.lang.reflect.InvocationTargetException;

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
    <T> boolean add(T object, Catalog<T> catalog);

    /**
     * Retrieves an object from a catalog at the specified index.
     *
     * @param index   The index of the object to be retrieved.
     * @param catalog The catalog from which the object will be retrieved.
     * @param <T>     The type of the object.
     * @return The object at the specified index.
     */
    <T> T get(int index, Catalog<T> catalog);

    /**
     * Retrieves the index of an object in a catalog.
     *
     * @param Obj     The object whose index is to be retrieved.
     * @param catalog The catalog from which the index will be retrieved.
     * @param <T>     The type of the object.
     * @return The index of the object in the catalog.
     */
    <T> int indexOf(T Obj, Catalog<T> catalog);

    /**
     * Retrieves the index of an object in a catalog by its ID.
     *
     * @param objId   The ID of the object whose index is to be retrieved.
     * @param catalog The catalog from which the index will be retrieved.
     * @param <T>     The type of the object.
     * @return The index of the object in the catalog.
     * @throws InvocationTargetException if the underlying method throws an exception.
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    <T> int indexById(int objId, Catalog<T> catalog) throws InvocationTargetException, IllegalAccessException;

    /**
     * Retrieves an object from a catalog by its ID.
     *
     * @param objId   The ID of the object to be retrieved.
     * @param catalog The catalog from which the object will be retrieved.
     * @param <T>     The type of the object.
     * @return The object with the specified ID.
     * @throws InvocationTargetException if the underlying method throws an exception.
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    <T> T getById(int objId, Catalog<T> catalog) throws InvocationTargetException, IllegalAccessException;

    //endregion

    /**
     * Retrieves an object of type T associated with an object of type U.
     *
     * @param objT The class of the object to be retrieved.
     * @param objU The object associated with the object to be retrieved.
     * @param <T> The type of the object to be retrieved.
     * @param <U> The type of the associated object.
     * @return The object of type T associated with the object of type U.
     * @throws InvocationTargetException if the underlying method throws an exception.
     * @throws IllegalAccessException if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
}