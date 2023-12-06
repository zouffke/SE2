package be.kdg.hifresh.dal.aankoop;

import be.kdg.hifresh.domain.aankoop.AankoopFactory;
import be.kdg.hifresh.domain.aankoop.Contract;
import be.kdg.hifresh.domain.aankoop.Product;
import be.kdg.hifresh.domain.util.Eenheid;
import be.kdg.hifresh.domain.util.UtilFactory;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

public final class ContractController {

    //region vars

    @Setter
    private static ContractManager manager;

    //endregion

    //region constructors

    private ContractController() {
    }

    //endregion

    //region setup functions

    public static boolean addProduct(int prodId, String name) {
        return manager.addObjtoCatalog(
                AankoopFactory.createContract(
                        AankoopFactory.createProduct(prodId, name)
                ),
                manager.getContractCataloog()
        );
    }

    public static Product getProductFromCatalog(int prodId) throws InvocationTargetException, IllegalAccessException {
        return manager.getObjFromCatalogById(
                prodId,
                manager.getContractCataloog()
        ).getProduct();
    }

    public static boolean addCentrumToCatalog(int id, String name) {
        return manager.addObjtoCatalog(
                AankoopFactory.createDistributieCentrum(
                        id,
                        name
                ),
                manager.getDcCataloog()
        );
    }

    public static boolean addClausule(int id, int prodId, LocalDateTime start, LocalDateTime end, double hoeveelheid, Eenheid eenheid, double bedrag) throws InvocationTargetException, IllegalAccessException {
        Contract contract = manager.getObjFromCatalogById(
                prodId,
                manager.getContractCataloog()
        );

        return contract.addClausule(
                AankoopFactory.createClausule(
                        id,
                        contract,
                        UtilFactory.createPeriod(start, end),
                        hoeveelheid,
                        eenheid,
                        UtilFactory.createPrijsAfspraak(
                                UtilFactory.createMunt(bedrag, "Euro"),
                                hoeveelheid
                        )
                )
        );
    }

    //endregion
}