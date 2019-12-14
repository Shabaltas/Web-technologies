package by.training.finalproject.dao;

public class DAOFactory {
    private static volatile DAOFactory instance;
    private DAOFactory() {}
    public DAOFactory getInstance() {
        if (instance == null) {
            synchronized (DAOFactory.class) {
                if (instance == null) {
                    instance = new DAOFactory();
                }
            }
        }
        return instance;
    }

}
