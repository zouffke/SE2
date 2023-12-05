package be.kdg.hifresh.dal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that represents a catalog of objects of type T.
 * Provides methods for adding objects to the catalog, retrieving objects from the catalog,
 * and finding the index of an object in the catalog.
 */
public abstract class Catalog<T> {
    /**
     * The list of objects in the catalog.
     */
    private final List<T> list;

    /**
     * Constructor for Catalog.
     * Initializes the list of objects.
     */
    protected Catalog() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds an object to the catalog.
     *
     * @param obj The object to be added to the catalog.
     * @return true if the object was added successfully, false otherwise.
     */
    public boolean addObjToCatalog(T obj) {
        return this.list.add(obj);
    }

    /**
     * Retrieves an object from the catalog at the specified index.
     *
     * @param index The index of the object to be retrieved.
     * @return The object at the specified index.
     */
    public T getObjFromCatalog(int index) {
        return this.list.get(index);
    }

    /**
     * Returns the index of the specified object in the catalog.
     *
     * @param obj The object whose index is to be returned.
     * @return The index of the object, or -1 if the object is not found.
     */
    public int getIndexOfObj(T obj){
        return this.list.indexOf(obj);
    }

    /**
     * Returns the list of objects in the catalog.
     *
     * @return The list of objects in the catalog.
     */
    protected List<T> getList() {
        return this.list;
    }

    /**
     * Returns the index of the object with the specified id in the catalog.
     *
     * @param objId The id of the object.
     * @return The index of the object with the specified id, or -1 if no such object is found.
     * @throws InvocationTargetException if the underlying method getId throws an exception.
     * @throws IllegalAccessException if this Method object is enforcing Java language access control and the underlying method is inaccessible.
     */
    public int getIndexOfObjById(int objId) throws InvocationTargetException, IllegalAccessException {
        for (T t : list) {
            for (Method m : t.getClass().getDeclaredMethods()) {
                if (m.getName().equals("getId")) {
                    if ((int) m.invoke(t) == objId) {
                        return list.indexOf(t);
                    }
                }
            }
        }
        return -1;
    }
}