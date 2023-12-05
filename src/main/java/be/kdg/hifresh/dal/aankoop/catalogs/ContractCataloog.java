package be.kdg.hifresh.dal.aankoop.catalogs;

import be.kdg.hifresh.dal.Catalog;
import be.kdg.hifresh.domain.aankoop.Clausule;
import be.kdg.hifresh.domain.aankoop.Contract;
import be.kdg.hifresh.domain.aankoop.Product;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ContractCataloog extends Catalog<Contract> {

    public ContractCataloog() {
        super();
    }

    public Product getProduct(int prodId) {
        return super.getList().stream()
                .map(Contract::getProduct)
                .filter(p -> p.getId() == prodId)
                .findFirst().orElse(null);
    }

    @Override
    public int getIndexOfObjById(int objId) {
        return this.getContractByProdId(objId);
    }

    private int getContractByProdId(int prodId){
        Contract contract = super.getList().stream()
                .filter(c -> c.getProduct().getId() == prodId)
                .findFirst().orElse(null);
        if (contract == null) return -1;

        return super.getList().indexOf(contract);
    }

    public boolean addClausuleToContract(int prodId, Clausule clausule){
        Contract contract = super.getObjFromCatalog(this.getContractByProdId(prodId));
        if (contract == null) throw new IllegalArgumentException("Given product ID was not found in list.");

        return contract.addClausule(clausule);
    }
}