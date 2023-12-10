package be.kdg.hifresh.persistenceLayer.aankoop.catalogs;

import be.kdg.hifresh.persistenceLayer.Catalog;
import be.kdg.hifresh.businessLayer.aankoop.Contract;

/**
 * A catalog class for managing Contract objects.
 *
 * @author Dandois Luca
 */
public class ContractCataloog extends Catalog<Contract> {

    /**
     * Constructor for ContractCataloog.
     *
     * @author Dandois Luca
     */
    public ContractCataloog() {
        super();
    }

    /**
     * Returns the index of the Contract object with the given id.
     *
     * @param objId The id of the Contract object.
     * @return The index of the Contract object if found, -1 otherwise.
     *
     * @author Dandois Luca
     */
    @Override
    public int getIndexOfObjById(int objId) {
        return this.getContractByProdId(objId);
    }

    /**
     * Returns the index of the Contract object associated with the given product id.
     *
     * @param prodId The id of the product.
     * @return The index of the Contract object if found, -1 otherwise.
     *
     * @author Dandois Luca
     */
    private int getContractByProdId(int prodId){
        Contract contract = super.getList().stream()
                .filter(c -> c.getProduct().getId() == prodId)
                .findFirst().orElse(null);
        if (contract == null) return -1;

        return super.getList().indexOf(contract);
    }
}