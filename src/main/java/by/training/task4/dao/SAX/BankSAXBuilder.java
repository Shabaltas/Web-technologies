package by.training.task4.dao.SAX;


import by.training.task4.dao.AbstractBanksBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class BankSAXBuilder extends AbstractBanksBuilder {
    private static final Logger LOGGER = LogManager.getLogger(BankSAXBuilder.class.getSimpleName());
    private SAXHandler handler;
    private SAXParser parser;
    private String schemaName = "data/Banks.xsd";
    public BankSAXBuilder() throws SAXException {
        super();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(schemaName)));
        factory.setNamespaceAware(true);
        factory.setValidating(false);
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            LOGGER.warn(e);
        }
        handler = new SAXHandler();
    }

    public void setSchemaName(String schemaname) {
        schemaName = schemaname;
    }
    @Override
    public void buildBanks(String filename) {
        try {
            parser.parse(filename, handler);
        } catch (SAXException | IOException e) {
            LOGGER.warn(e);
        }
        banks.setDeposits(handler.getDeposits());
    }
}
