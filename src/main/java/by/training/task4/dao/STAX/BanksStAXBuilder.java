package by.training.task4.dao.STAX;

import by.training.task4.dao.AbstractBanksBuilder;
import by.training.task4.dao.BankTag;
import by.training.task4.dao.SAX.BankSAXBuilder;
import by.training.task4.model.Deposit;
import by.training.task4.model.DepositForJuridicalPerson;
import by.training.task4.model.DepositForNaturalPerson;
import by.training.task4.model.SNP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Period;
import java.util.EnumSet;

public class BanksStAXBuilder extends AbstractBanksBuilder {
    private static final Logger LOGGER = LogManager.getLogger(BankSAXBuilder.class.getSimpleName());
    private XMLInputFactory inputFactory;
    private EnumSet<BankTag> depositChildren = EnumSet.of(BankTag.DEPOSIT_FOR_NATURAL_PERSON,
            BankTag.DEPOSIT_FOR_JURIDICAL_PERSON);
    public BanksStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }


    @Override
    public void buildBanks(String filename) {
        XMLStreamReader reader;
        BankTag tag;
        Deposit deposit;
        try (FileInputStream inputStream = new FileInputStream(new File(filename))){
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    tag = BankTag.fromValue(reader.getLocalName());
                    if (depositChildren.contains(tag)) {
                        if (tag == BankTag.DEPOSIT_FOR_JURIDICAL_PERSON) {
                            deposit = new DepositForJuridicalPerson();
                        } else {
                            deposit = new DepositForNaturalPerson();
                        }
                        buildDeposit(reader, deposit);
                        banks.add(deposit);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.warn("File " + filename + " wasn't found");
        } catch (IOException e) {
            LOGGER.warn(e);
        } catch (XMLStreamException e) {
            LOGGER.warn(e);
        } catch (DatatypeConfigurationException e) {
            LOGGER.warn("Configuration exception: " + e);
        }
    }

    private void buildDeposit(XMLStreamReader reader, Deposit deposit) throws XMLStreamException, DatatypeConfigurationException {
        deposit.setType(reader.getAttributeValue(null, BankTag.TYPE.getValue()));
        deposit.setAccountID(reader.getAttributeValue(null, BankTag.ACCOUNT_ID.getValue()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (BankTag.fromValue(name)) {
                        case BANK:
                            deposit.setName(getXMLText(reader));
                            break;
                        case PROFITABILITY:
                            deposit.setProfitability(Float.valueOf(getXMLText(reader)));
                            break;
                        case COUNTRY:
                            deposit.setCountry(getXMLText(reader));
                            break;
                        case DATE:
                            deposit.setDateOfDeposit(DatatypeFactory.newInstance().newXMLGregorianCalendar(getXMLText(reader)));
                            break;
                        case DURATION:
                            String duration = getXMLText(reader);
                            int t = duration.indexOf("T");
                            deposit.setDuration(Period.parse(duration.subSequence(0, t == -1 ? duration.length() : t)));
                            break;
                        case AMOUNT:
                             if (deposit.getClass().equals(DepositForJuridicalPerson.class)) {
                                 ((DepositForJuridicalPerson)deposit).setAmount(new BigDecimal(getXMLText(reader)));
                             } else {
                                 ((DepositForNaturalPerson)deposit).setAmount(new BigDecimal(getXMLText(reader)));
                             }
                            break;
                        case DEPOSITOR:
                            SNP depositor = buildSNP(reader);
                            ((DepositForNaturalPerson)deposit).setDepositorName(depositor);
                            break;
                        case CONTACT_PERSON:
                            SNP person = buildSNP(reader);
                            ((DepositForJuridicalPerson)deposit).setContactPerson(person);
                            break;
                        case COMPANY:
                            ((DepositForJuridicalPerson)deposit).setCompanyName(getXMLText(reader));
                            break;
                        case COMPANY_LOCATION:
                            ((DepositForJuridicalPerson)deposit).setCompanyLocation(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (depositChildren.contains(BankTag.fromValue(reader.getLocalName()))) {
                        return;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag depositForNaturalPerson");
    }
    private SNP buildSNP(XMLStreamReader reader) throws XMLStreamException {
        SNP person = new SNP();
        int type;
        BankTag tag;
        while (reader.hasNext()) {
            type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                tag = BankTag.fromValue(reader.getLocalName());
                switch (tag) {
                    case NAME:
                        person.setName(getXMLText(reader));
                        break;
                    case SURNAME:
                        person.setSurname(getXMLText(reader));
                        break;
                    case PATRONYMIC:
                        person.setPatronymic(getXMLText(reader));
                        break;
                    default:
                        throw new XMLStreamException("Unknown element in tag contactPerson or depositor");
                }
            }
            if (type == XMLStreamConstants.END_ELEMENT) {
                tag = BankTag.fromValue(reader.getLocalName());
                if (BankTag.CONTACT_PERSON == tag
                    || BankTag.DEPOSITOR == tag) {
                    return person;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag contactPerson or depositor");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text == null ? "" : text;
    }

}
