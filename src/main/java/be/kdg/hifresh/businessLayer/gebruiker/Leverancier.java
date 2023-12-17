package be.kdg.hifresh.businessLayer.gebruiker;

import be.kdg.hifresh.businessLayer.aankoop.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Bedrijf dat ingredienten levert.
 */
public class Leverancier extends Rol {
    private List<Contract> contracts;
    private int reputatie;

    private final int id;
    private String name;

    Leverancier(int id, String name) {
        this.id = id;
        this.name = name;
        this.contracts = new ArrayList<>();
    }

    public boolean addContract(Contract contract){
        return this.contracts.add(contract);
    }
}