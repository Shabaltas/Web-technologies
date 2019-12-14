package by.training.task4.dao;

public enum BankTag {
    BANKS("Banks"),
    TYPE("depositType"),
    ACCOUNT_ID("accountID"),
    DEPOSIT_FOR_NATURAL_PERSON("depositForNaturalPerson"),
    DEPOSIT_FOR_JURIDICAL_PERSON("depositForJuridicalPerson"),
    CONTACT_PERSON("contactPerson"),
    DEPOSITOR("depositor"),

    BANK("bankName"),
    PROFITABILITY("profitability"),
    COUNTRY("country"),
    DATE("dateOfDeposit"),
    DURATION("duration"),
    NAME("name"),
    SURNAME("surname"),
    PATRONYMIC("patronymic"),
    AMOUNT("amount"),
    COMPANY("companyName"),
    COMPANY_LOCATION("companyLocation");
    private final String value;

    BankTag(String v) {
        value = v;
    }

    public String getValue() {
        return value;
    }

    public static BankTag fromValue(String v) {
        for (BankTag type : BankTag.values()) {
            if (type.value.equals(v)) {
                return type;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
