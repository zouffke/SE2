package be.kdg.hifresh.domain.recepten;

import java.util.ArrayList;
import java.util.List;

public class ReceptCataloog {

    private final List<Recept> recepten;

    ReceptCataloog(){
        this.recepten = new ArrayList<>();
    }

    boolean addRecept(Recept recept) {
        return recepten.add(recept);
    }

    Recept getRecept(int id) {
        return recepten.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }
}