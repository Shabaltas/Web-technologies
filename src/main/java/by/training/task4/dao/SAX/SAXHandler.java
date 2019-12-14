package by.training.task4.dao.SAX;

import by.training.task4.model.Deposit;
import by.training.task4.model.DepositForJuridicalPerson;
import by.training.task4.model.DepositForNaturalPerson;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import by.training.task4.dao.BankTag;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.time.Period;
import java.util.*;

public class SAXHandler extends DefaultHandler {
    private List<Deposit> deposits;
    private Deposit current;
    private BankTag currentTagWithText;
    private EnumSet<BankTag> depositChildren = EnumSet.of(BankTag.DEPOSIT_FOR_NATURAL_PERSON,
                                                 BankTag.DEPOSIT_FOR_JURIDICAL_PERSON);
    private EnumSet<BankTag> withText = EnumSet.range(BankTag.BANK, BankTag.COMPANY_LOCATION);

    public SAXHandler() {
        deposits = new ArrayList<>();
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            BankTag currentTag = BankTag.fromValue(qName);
            if (depositChildren.contains(currentTag)) {
                if (currentTag == BankTag.DEPOSIT_FOR_NATURAL_PERSON) {
                    current = new DepositForNaturalPerson();
                } else {
                    current = new DepositForJuridicalPerson();
                }
                for (int i = 0; i < attributes.getLength(); i++) {
                    String currentAttr = attributes.getLocalName(i);
                    String currentVal = attributes.getValue(i);
                    if (BankTag.ACCOUNT_ID.getValue().equals(currentAttr)) {
                        current.setAccountID(currentVal);
                    }
                    if (BankTag.TYPE.getValue().equals(currentAttr)) {
                        current.setType(currentVal);
                    }
                }
            } else if (withText.contains(currentTag)) {
                currentTagWithText = currentTag;
            }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length).trim();
        if (currentTagWithText != null) {
            switch (currentTagWithText) {
                case BANK:
                    current.setName(content);
                    break;
                case PROFITABILITY:
                    current.setProfitability(Float.valueOf(content));
                    break;
                case COUNTRY:
                    current.setCountry(content);
                    break;
                case DATE:
                    try {
                        current.setDateOfDeposit(DatatypeFactory.newInstance().newXMLGregorianCalendar(content));
                    } catch (DatatypeConfigurationException e) {
                        e.printStackTrace();
                    }
                    break;
                case DURATION:
                    int t = content.indexOf("T");
                    current.setDuration(Period.parse(content.subSequence(0, t == -1 ? content.length() : t)));
                    break;
                case NAME:
                    if (current.getClass().equals(DepositForNaturalPerson.class)) {
                        ((DepositForNaturalPerson)current).getDepositorName().setName(content);
                    } else {
                        ((DepositForJuridicalPerson)current).getContactPerson().setName(content);
                    }
                    break;
                case PATRONYMIC:
                    if (current.getClass().equals(DepositForNaturalPerson.class)) {
                        ((DepositForNaturalPerson)current).getDepositorName().setPatronymic(content);
                    } else {
                        ((DepositForJuridicalPerson)current).getContactPerson().setPatronymic(content);
                    }
                    break;
                case SURNAME:
                    if (current.getClass().equals(DepositForNaturalPerson.class)) {
                        ((DepositForNaturalPerson)current).getDepositorName().setSurname(content);
                    } else {
                        ((DepositForJuridicalPerson)current).getContactPerson().setSurname(content);
                    }
                    break;
                case AMOUNT:
                    if (current.getClass().equals(DepositForNaturalPerson.class)) {
                        ((DepositForNaturalPerson)current).setAmount(new BigDecimal(content));
                    } else {
                        ((DepositForJuridicalPerson)current).setAmount(new BigDecimal(content));
                    }
                    break;
                case COMPANY:
                    ((DepositForJuridicalPerson)current).setCompanyName(content);
                    break;
                case COMPANY_LOCATION:
                    ((DepositForJuridicalPerson)current).setCompanyLocation(content);
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentTagWithText.getDeclaringClass(), currentTagWithText.name());
            }
        }
        currentTagWithText = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (depositChildren.contains(BankTag.fromValue(qName))){
            deposits.add(current);
        }
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }
}
