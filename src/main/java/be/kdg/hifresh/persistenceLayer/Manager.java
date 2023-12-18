package be.kdg.hifresh.persistenceLayer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * An abstract class that provides a base for all manager classes.
 * Implements the IManager interface.
 */
public abstract class Manager implements IManager {

    //region catalog functions

    /**
     * Adds an object to a catalog.
     *
     * @param object  The object to be added to the catalog.
     * @param catalog The catalog to which the object will be added.
     * @param <T>     The type of the object.
     * @return true if the object was added successfully, false otherwise.
     */
    @Override
    public <T> boolean add(T object, Catalog<T> catalog) {
        return catalog.add(object);
    }

    /**
     * Retrieves an object from a catalog at the specified index.
     *
     * @param index   The index of the object to be retrieved.
     * @param catalog The catalog from which the object will be retrieved.
     * @param <T>     The type of the object.
     * @return The object at the specified index.
     */
    @Override
    public <T> T get(int index, Catalog<T> catalog) {
        return catalog.get(index);
    }

    /**
     * Retrieves the index of an object in a catalog.
     *
     * @param obj The object whose index is to be retrieved.
     * @param catalog The catalog from which the index will be retrieved.
     * @param <T> The type of the object.
     * @return The index of the object in the catalog.
     */
    @Override
    public <T> int indexOf(T obj, Catalog<T> catalog) {
        return catalog.indexOf(obj);
    }

    /**
     * Retrieves the index of an object in a catalog by its ID.
     *
     * @param objId The ID of the object whose index is to be retrieved.
     * @param catalog The catalog from which the index will be retrieved.
     * @param <T> The type of the object.
     * @return The index of the object in the catalog.
     * @throws InvocationTargetException if the underlying method throws an exception.
     * @throws IllegalAccessException if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    @Override
    public <T> int indexById(int objId, Catalog<T> catalog) throws InvocationTargetException, IllegalAccessException {
        return catalog.indexById(objId);
    }

    /**
     * Retrieves an object from a catalog by its ID.
     *
     * @param objId The ID of the object to be retrieved.
     * @param catalog The catalog from which the object will be retrieved.
     * @param <T> The type of the object.
     * @return The object with the specified ID.
     * @throws InvocationTargetException if the underlying method throws an exception.
     * @throws IllegalAccessException if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    @Override
    public <T> T getById(int objId, Catalog<T> catalog) throws InvocationTargetException, IllegalAccessException {
        return catalog.get(catalog.indexById(objId));
    }

    //endregion
}