package be.kdg.hifresh.businessLayer.gebruiker;

import be.kdg.hifresh.businessLayer.aankoop.Contract;
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
    private final String NAME;
    private int reputatie;

    Leverancier(int id, String name) {
        this.ID = id;
        this.NAME = name;
        this.CONTRACTS = new ArrayList<>();
    }

    public boolean addContract(Contract contract) {
        return this.CONTRACTS.add(contract);
    }
}