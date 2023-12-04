package be.kdg.hifresh.domain.aankoop;

import java.util.ArrayList;
import java.util.List;

public class ContractCataloog {

    private final List<Contract> contracten;

    ContractCataloog() {
        this.contracten = new ArrayList<>();
    }

    boolean addContract(Contract contract) {
        return this.contracten.add(contract);
    }

    Product getProduct(int prodId) {
        return this.contracten.stream()
                .map(Contract::getProduct)
                .filter(p -> p.getId() == prodId)
                .findFirst().orElse(null);
    }
}