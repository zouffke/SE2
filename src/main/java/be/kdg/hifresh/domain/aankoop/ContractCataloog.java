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

    Contract getContractByProdId(int prodId){
        return this.contracten.stream()
                .filter(c -> c.getProduct().getId() == prodId)
                .findFirst().orElse(null);
    }

    boolean addClausuleToContract(int prodId, Clausule clausule){
        Contract contract = this.getContractByProdId(prodId);
        if (contract == null) throw new IllegalArgumentException("Given product ID was not found in list.");

        return contract.addClausule(clausule);
    }
}