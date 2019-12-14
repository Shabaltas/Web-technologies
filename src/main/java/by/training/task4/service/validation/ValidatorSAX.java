package by.training.task4.service.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class ValidatorSAX {
    private static final Logger LOGGER = LogManager.getLogger(ValidatorSAX.class.getSimpleName());
    public static void main(String[] args) {
        String filename = "data/Banks2.xml";
        String schemaname = "data/Banks.xsd";
        Schema schema = null;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
            schema = factory.newSchema(new File(schemaname));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setSchema(schema);
            SAXParser parser = spf.newSAXParser();
            parser.parse(filename, new BanksErrorHandler());
            LOGGER.info(filename + " is valid");
        } catch (ParserConfigurationException e) {
            LOGGER.warn(filename  + " config error: " + e.getMessage());
        } catch (SAXException e) {
            LOGGER.warn(filename  + " by.training.task4.dao.builder.SAX error: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.warn("I/O error: " + e.getMessage());
        }
    }
}
