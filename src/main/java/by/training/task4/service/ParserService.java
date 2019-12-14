package by.training.task4.service;

import by.training.task4.dao.AbstractBanksBuilder;
import by.training.task4.dao.BanksBuilderFactory;
import by.training.task4.model.Banks;
import org.xml.sax.SAXException;

public class ParserService {
    public Banks parseXML(String parserType, String fileXML) throws SAXException {
        AbstractBanksBuilder builder = BanksBuilderFactory.getInstance().createBanksBuilder(parserType);
        builder.buildBanks(fileXML);
        return builder.getBanks();
    }
}
