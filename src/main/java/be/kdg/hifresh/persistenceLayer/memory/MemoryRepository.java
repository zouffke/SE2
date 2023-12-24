package be.kdg.hifresh.persistenceLayer.memory;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class MemoryRepository {

    private final List<Object> memory;

    public MemoryRepository() {
        this.memory = new ArrayList<>();
    }

    public void add(Object item) {
        memory.add(item);
    }

    public Object get(Class<?> type) {
        for (Object item : memory) {
            if (item.getClass().equals(type)) {
                return item;
            }
        }
        return null;
    }
}
