package by.training.task4.service.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidatorSAXXSD {
    private static final Logger LOGGER = LogManager.getLogger(ValidatorSAXXSD.class.getSimpleName());
    public static void main(String[] args) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String filename = "data/Banks2.xml";
        String schemaname = "data/Banks.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaname);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            BanksErrorHandler handler = new BanksErrorHandler();
            validator.setErrorHandler(handler);
            Source source = new StreamSource(filename);
            validator.validate(source);
            LOGGER.info(filename + " validating is ended");
        } catch (SAXException e) {
            LOGGER.warn("by/training/task4/service/validation " + filename + " is not valid because " + e.getMessage());
        } catch (IOException e) {
            LOGGER.warn(filename + " is not valid because " + e.getMessage());
        }
    }
}
