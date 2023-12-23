package be.kdg.hifresh.persistenceLayer;

import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * An abstract class that represents a catalog of objects of type T.
 * Provides methods for adding objects to the catalog, retrieving objects from the catalog,
 * and finding the index of an object in the catalog.
 */
@Getter
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
    public boolean add(T obj) {
        return this.list.add(obj);
    }

    /**
     * Retrieves an object from the catalog at the specified index.
     *
     * @param index The index of the object to be retrieved.
     * @return The object at the specified index.
     */
    public T get(int index) {
        return this.list.get(index);
    }

    /**
     * Returns the index of the object with the specified id in the catalog.
     *
     * @param objId The id of the object.
     * @return The index of the object with the specified id, or -1 if no such object is found.
     */
    public int indexById(int objId) {
        return IntStream.range(0, list.size())
                .filter(i -> {
                    T t = list.get(i);
                    return Stream.concat(Arrays.stream(t.getClass().getDeclaredMethods()),
                                    Stream.of(t.getClass().getSuperclass().getDeclaredMethods()))
                            .anyMatch(m -> {
                                try {
                                    return m.getName().equals("getID") && (int) m.invoke(t) == objId;
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                })
                .findFirst()
                .orElse(-1);
    }

    public List<T> getByName(String name) throws InvocationTargetException, IllegalAccessException {
        List<T> filtered = new ArrayList<>();
        for (T t : list) {
            for (Method m : t.getClass().getDeclaredMethods()) {
                if (m.getName().equals("getNAME")) {
                    String result = (String) m.invoke(t);
                    if (result.toUpperCase().contains(name.toUpperCase())) {
                        filtered.add(t);
                    }
                }
            }
        }
        return filtered;
    }
}