package by.training.task4.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DepositType")
@XmlEnum
public enum DepositType {
    @XmlEnumValue("poste reastante")
    POSTE_RESTANTE("poste restante"),
    @XmlEnumValue("urgent")
    URGENT("urgent"),
    @XmlEnumValue("settlement")
    SETTLEMENT("settlement"),
    @XmlEnumValue("accumulative")
    ACCUMULATIVE("accumulative"),
    @XmlEnumValue("saving")
    SAVING("saving"),
    @XmlEnumValue("metallic")
    METALLIC("metallic");

    private final String value;

    DepositType(String v) {
        value = v;
    }

    public String getValue() {
        return value;
    }

    public static DepositType fromValue(String v) {
        for (DepositType type : DepositType.values()) {
            if (type.value.equals(v)) {
                return type;
            }
        }
        throw new IllegalArgumentException(v);
    }
}