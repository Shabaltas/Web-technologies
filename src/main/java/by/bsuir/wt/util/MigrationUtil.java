package by.bsuir.wt.util;

import by.bsuir.wt.model.Banks;
import by.bsuir.wt.model.Deposit;
import by.bsuir.wt.model.DepositForJuridicalPerson;
import by.bsuir.wt.model.DepositForNaturalPerson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.*;

public class MigrationUtil {
    private volatile static MigrationUtil migrationUtil;
    private Connection connection;
    private String driverClass;
    private String url;
    private String user;
    private String password;

    private MigrationUtil(String driverClass, String url, String user, String password) {
        this.driverClass = driverClass;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static MigrationUtil getMigrationUtil(String driverClass, String url, String user, String password) {
        if (migrationUtil == null) {
            synchronized (MigrationUtil.class) {
                if (migrationUtil == null) {
                    migrationUtil = new MigrationUtil(driverClass, url, user, password);
                }
            }
        }
        return migrationUtil;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Call {@code when you finish with updating database}
     * @param path
     * @return
     */
    public boolean migrate(String path) {
        try {
            if (connection == null) {
                openConnection();
            }
            Banks banksFromXml = readFromXml(path);
            String insertJuridical = "INSERT INTO `BANK.deposit` (`JorN`, `name`, `country`, `profitability`," +
                    " `duration`, `dateOfDeposit`, `depositType`, `accountId`, `contactPersonSurname`, `contactPersonName`, " +
                    "`contactPersonPatronymic`, `amount`, `company`, `location`) " +
                    "VALUES ('J',?,?,?,?,?,?,?,?,?,?,?,?,?);";

            String insertNatural = "INSERT INTO `BANK.deposit` (`JorN`, `name`, `country`, `profitability`," +
                    " `duration`, `dateOfDeposit`, `depositType`, `accountId`, `depositorSurname`, `depositorName`, " +
                    "`depositorPatronymic`, `amount`) " +
                    "VALUES ('N',?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement;
            for (Deposit deposit : banksFromXml.getDeposits()) {
                if (deposit instanceof DepositForNaturalPerson) {
                    preparedStatement = connection.prepareStatement(insertNatural);
                    setAllNaturalFields(preparedStatement, (DepositForNaturalPerson) deposit);
                } else {
                    preparedStatement = connection.prepareStatement(insertJuridical);
                    setAllJuridicalFields(preparedStatement, (DepositForJuridicalPerson) deposit);
                }
                preparedStatement.executeUpdate();
            }
            return true;
        } catch (SQLException | ClassNotFoundException | JAXBException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Creates connection to database.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void openConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driverClass);
        connection = DriverManager.getConnection(url, user, password);
    }

    private Banks readFromXml(String path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Banks.class);
        Unmarshaller um = context.createUnmarshaller();
        return (Banks) um.unmarshal(new File(path));
    }

    private void setAllJuridicalFields(PreparedStatement statement, DepositForJuridicalPerson deposit) throws SQLException {
        setAllFields(statement, deposit);
        statement.setString(9, deposit.getContactPerson().getSurname());
        statement.setString(10, deposit.getContactPerson().getName());
        statement.setString(11, deposit.getContactPerson().getPatronymic());
        statement.setBigDecimal(12, deposit.getAmount());
        statement.setString(13, deposit.getCompanyName());
        statement.setString(14, deposit.getCompanyLocation());
    }

    private void setAllNaturalFields(PreparedStatement statement, DepositForNaturalPerson deposit) throws SQLException {
        setAllFields(statement, deposit);
        statement.setString(9, deposit.getDepositor().getSurname());
        statement.setString(10, deposit.getDepositor().getName());
        statement.setString(11, deposit.getDepositor().getPatronymic());
        statement.setBigDecimal(12, deposit.getAmount());
    }

    private void setAllFields(PreparedStatement statement, Deposit deposit) throws SQLException {
        statement.setString(2, deposit.getName());
        statement.setString(3, deposit.getCountry());
        statement.setFloat(4, deposit.getProfitability());
        statement.setString(5, String.valueOf(deposit.getDuration()));
        statement.setString(6, String.valueOf(deposit.getDateOfDeposit()));
        statement.setString(7, deposit.getType().getValue());
        statement.setString(8, deposit.getAccountID());
    }
}
