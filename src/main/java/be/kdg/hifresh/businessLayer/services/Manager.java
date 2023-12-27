package be.kdg.hifresh.businessLayer.services;

import be.kdg.hifresh.persistenceLayer.Catalog;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that provides a base for all manager classes.
 * Implements the IManager interface.
 */
public abstract class Manager {

    //region vars

    List<Catalog<?>> catalogs;

    //endregion

    //region constructors

    protected Manager() {
        this.catalogs = new ArrayList<>();
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


    public <T> T get(int index, Catalog<T> catalog) {
        return catalog.get(index);
    }


    public <T> T getById(int objId, Catalog<T> catalog) {
        return catalog.get(catalog.indexById(objId));
    }

    public abstract void clearCatalogs();
    //endregion
}