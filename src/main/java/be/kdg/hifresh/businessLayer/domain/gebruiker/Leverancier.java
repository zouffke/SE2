package be.kdg.hifresh.businessLayer.domain.gebruiker;

import be.kdg.hifresh.businessLayer.domain.aankoop.Contract;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Bedrijf dat ingredienten levert.
 */
public class Leverancier extends Rol {
    private final List<Contract> CONTRACTS;
    @Getter
    private final int ID;
    @SuppressWarnings({"unused", "FieldCanBeLocal"})
    private final String NAME;
    @SuppressWarnings("unused")
    private int reputatie;

    Leverancier(int id, String name) {
        this.ID = id;
        this.NAME = name;
        this.CONTRACTS = new ArrayList<>();
    }

    public void addContract(Contract contract) {
        this.CONTRACTS.add(contract);
    }
}