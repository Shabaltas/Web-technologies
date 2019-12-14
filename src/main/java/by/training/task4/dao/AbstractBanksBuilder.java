package by.training.task4.dao;

import by.training.task4.model.Banks;

public abstract class AbstractBanksBuilder {
    protected Banks banks;
    public AbstractBanksBuilder() {
        banks = new Banks();
    }

    public Banks getBanks() {
        return banks;
    }
    abstract public void buildBanks(String filename);
}
