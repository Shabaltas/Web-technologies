package by.training.task4.dao.DOM;

import by.training.task4.dao.AbstractBanksBuilder;
import by.training.task4.model.Deposit;
import by.training.task4.model.DepositForJuridicalPerson;
import by.training.task4.model.DepositForNaturalPerson;
import by.training.task4.model.SNP;
import by.training.task4.dao.BankTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Period;

public class BanksDOMBuilder extends AbstractBanksBuilder {

    private static final Logger LOGGER = LogManager.getLogger(BanksDOMBuilder.class.getSimpleName());
    private String schemaName = "data/Banks.xsd";
    private DocumentBuilder docBuilder;

    public BanksDOMBuilder() throws SAXException {
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setSchema(SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(new File(schemaName)));
        factory.setNamespaceAware(true);
        factory.setValidating(false);
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error(e);
        }
    }

    public void setSchemaName(String schemaname) {
        schemaName = schemaname;
    }

    @Override
    public void buildBanks(String filename) {
        Document doc;
        try {
            doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList depositsForNatural = root.getElementsByTagName(BankTag.DEPOSIT_FOR_NATURAL_PERSON.getValue());
            NodeList depositsForJuridical = root.getElementsByTagName(BankTag.DEPOSIT_FOR_JURIDICAL_PERSON.getValue());
            for (int i = 0; i < depositsForNatural.getLength(); i++) {
                Element depositElement = (Element) depositsForNatural.item(i);
                DepositForNaturalPerson deposit = buildDepositForNatural(depositElement);
                banks.add(deposit);
            }
            for (int i = 0; i < depositsForJuridical.getLength(); i++) {
                Element depositElement = (Element) depositsForJuridical.item(i);
                DepositForJuridicalPerson deposit = buildDepositForJuridical(depositElement);
                banks.add(deposit);
            }
        } catch (SAXException e) {
            LOGGER.warn("Parsing failure: " + e);
        } catch (IOException e) {
            LOGGER.warn("File error or I/O error: " + e);
        } catch (DatatypeConfigurationException e) {
            LOGGER.warn("Datatype configuration error: " + e);
        }
    }

    private DepositForNaturalPerson buildDepositForNatural(Element element) throws DatatypeConfigurationException {
        DepositForNaturalPerson deposit = new DepositForNaturalPerson();
        buildDeposit(element, deposit);
        deposit.setAmount(new BigDecimal(getElementTextContent(element, BankTag.AMOUNT)));
        buildSNP(element, deposit.getDepositorName());
        return deposit;
    }

    private DepositForJuridicalPerson buildDepositForJuridical(Element element) throws DatatypeConfigurationException {
        DepositForJuridicalPerson deposit = new DepositForJuridicalPerson();
        buildDeposit(element, deposit);
        deposit.setAmount(new BigDecimal(getElementTextContent(element, BankTag.AMOUNT)));
        buildSNP(element, deposit.getContactPerson());
        deposit.setCompanyName(getElementTextContent(element, BankTag.COMPANY));
        deposit.setCompanyLocation(getElementTextContent(element, BankTag.COMPANY_LOCATION));
        return deposit;
    }

    private void buildSNP(Element element, SNP person) {
        person.setSurname(getElementTextContent(element, BankTag.SURNAME));
        person.setName(getElementTextContent(element, BankTag.NAME));
        person.setPatronymic(getElementTextContent(element, BankTag.PATRONYMIC));
    }
    private void buildDeposit(Element element, Deposit deposit) throws DatatypeConfigurationException {
        deposit.setType(element.getAttribute(BankTag.TYPE.getValue()));
        deposit.setAccountID(element.getAttribute(BankTag.ACCOUNT_ID.getValue()));
        deposit.setName(getElementTextContent(element, BankTag.BANK));
        deposit.setProfitability(Float.valueOf(getElementTextContent(element, BankTag.PROFITABILITY)));
        deposit.setCountry(getElementTextContent(element, BankTag.COUNTRY));
        deposit.setDateOfDeposit(DatatypeFactory.newInstance().newXMLGregorianCalendar(getElementTextContent(element, BankTag.DATE)));
        String duration = getElementTextContent(element, BankTag.DURATION);
        int t = duration.indexOf("T");
        deposit.setDuration(Period.parse(duration.subSequence(0, t == -1 ? duration.length() : t)));
    }

    private String getElementTextContent(Element element, BankTag tag) {
        NodeList nList = element.getElementsByTagName(tag.getValue());
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
