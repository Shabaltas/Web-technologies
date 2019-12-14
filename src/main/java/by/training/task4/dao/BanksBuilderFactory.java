package by.training.task4.dao;

import by.training.task4.dao.SAX.BankSAXBuilder;
import by.training.task4.dao.STAX.BanksStAXBuilder;
import by.training.task4.dao.DOM.BanksDOMBuilder;
import org.xml.sax.SAXException;

public class BanksBuilderFactory {
    private static volatile BanksBuilderFactory instance;
    private enum TypeParser {
        SAX, DOM, STAX
    }

    private BanksBuilderFactory() {}

    public static BanksBuilderFactory getInstance() {
        if (instance == null) {
            synchronized (BanksBuilderFactory.class) {
                if (instance == null) {
                    instance = new BanksBuilderFactory();
                }
            }
        }
        return instance;
    }
    public AbstractBanksBuilder createBanksBuilder(String typeParser) throws SAXException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new BanksDOMBuilder();
            case SAX:
                return new BankSAXBuilder();
            case STAX:
                return new BanksStAXBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
