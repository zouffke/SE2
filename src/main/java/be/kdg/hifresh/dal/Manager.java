package be.kdg.hifresh.dal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * An abstract class that provides a base for all manager classes.
 * Implements the IManager interface.
 */
public abstract class Manager implements IManager {

    /**
     * Adds an object to a catalog.
     *
     * @param object  The object to be added to the catalog.
     * @param catalog The catalog to which the object will be added.
     * @param <T>     The type of the object.
     * @return true if the object was added successfully, false otherwise.
     */
    @Override
    public <T> boolean addTtoCatalog(T object, Catalog<T> catalog) {
        return catalog.addObjToCatalog(object);
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
    public <T> T getObjFromCatalog(int index, Catalog<T> catalog) {
        return catalog.getObjFromCatalog(index);
    }

    /**
     * Retrieves an object of type T associated with an object of type U.
     *
     * @param objT The class of the object to be retrieved.
     * @param objU The object associated with the object to be retrieved.
     * @param <T>  The type of the object to be retrieved.
     * @param <U>  The type of the associated object.
     * @return The object of type T associated with the object of type U.
     * @throws InvocationTargetException if the underlying method throws an exception.
     * @throws IllegalAccessException    if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T, U> T GetObjTFromObjU(Class<T> objT, U objU) throws InvocationTargetException, IllegalAccessException {
        for (Method m : objU.getClass().getDeclaredMethods()) {
            if (m.getName().toLowerCase().equals("get" + objT.getSimpleName().toLowerCase())) {
                return (T) m.invoke(objT);
            }
        }
        return null;
    }
}