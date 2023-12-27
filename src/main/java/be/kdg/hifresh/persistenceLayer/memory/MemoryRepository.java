package be.kdg.hifresh.persistenceLayer.memory;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an in-memory repository.
 * It is used to store and retrieve objects in memory.
 * It is annotated as a Spring component, so it can be automatically detected and instantiated by Spring.
 */
@Getter
@Component
public class MemoryRepository {

    /**
     * List used to store objects in memory.
     */
    private final List<Object> memory;

    /**
     * Default constructor for MemoryRepository.
     * Initializes the memory list.
     */
    public MemoryRepository() {
        this.memory = new ArrayList<>();
    }

    /**
     * Adds an object to the memory list.
     *
     * @param item The object to be added to the memory list.
     */
    public void add(Object item) {
        memory.add(item);
    }

    /**
     * Retrieves an object of a specific type from the memory list.
     *
     * @param type The class of the object to be retrieved.
     * @return The object of the specified type, or null if no such object is found.
     */
    public Object get(Class<?> type) {
        for (Object item : memory) {
            if (item.getClass().equals(type)) {
                return item;
            }
        }
        return null;
    }
}