package by.training.finalproject.dao.sql;

import by.training.finalproject.dao.DaoException;
import by.training.finalproject.entity.Person;
import by.training.finalproject.entity.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class PersonDaoImpl extends BaseDaoImpl<Person> {
    private static final String INSERT_QUERY = "INSERT INTO `person` (`surname`, `name`, `email`, `phone`, `passwordHash`, `role`) VALUES (?,?,?,?,?,?);";
    private static final String SELECT_ALL = "SELECT `id`, `surname`, `name`, `email`, `phone`, `passwordHash`, `role` FROM `person` ORDER BY `id`;";
    private static final String SELECT_FOR_LOGIN = "SELECT `id`, `surname`, `name`, `role` FROM `person` WHERE `email`=? AND `passwordHash`=?;";
    private static final String UPDATE = "UPDATE `person` SET `surname`=?, `name`=?, `email`=?, `phone`=?, `passwordHash`=?, `role`=? WHERE `id` = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM `person` WHERE `id`=?;";
    private static final String SELECT_BY_ID = "SELECT `id`, `surname`, `name`, `email`, `phone`, `passwordHash`, `role` FROM `person` WHERE `id`=?;";
    protected static final String SELECT_BY_ROLE = "SELECT `id`, `surname`, `name`, `email`, `phone`, `passwordHash`, `role` FROM `person` WHERE `role`=?;";

    public void deletePerson(Person person) throws DaoException {
        defaultDelete(DELETE_BY_ID, person.getId());
    }

    public void deletePerson(long idPerson) throws DaoException {
        defaultDelete(DELETE_BY_ID, idPerson);
    }

    public void updatePerson(Person person) throws DaoException {
        defaultUpdate(UPDATE, person);
    }

    public List<Person> readByRole(Role role) throws DaoException {
        return defaultRead(SELECT_BY_ROLE, new String[]{role.getRoleValue()});
    }

   /* public Person readById(long id) throws DaoException {
        try {
            return defaultRead(SELECT_BY_ID, new String[]{String.valueOf(id)}).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new DaoException("There is no person with such identifier");
        }
    }*/

    public boolean signIn(String email, String passwordHash) throws DaoException {
        return defaultRead(SELECT_FOR_LOGIN, new String[]{email, passwordHash}).size() > 0;
    }

    public long register(Person person) throws DaoException {
        if (!signIn(person.getEmail(), person.getPasswordHash())) {
            return defaultInsert(INSERT_QUERY, person);
        }
        return 0;
    }

    @Override
    protected List<Person> fillList(ResultSet resultSet) throws DaoException {
        return null;
    }

    @Override
    protected void setAllFields(PreparedStatement statement, Person entity) throws DaoException {
        try {
            statement.setString(1, entity.getSurname());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPhone());
            statement.setString(5, entity.getPasswordHash());
            statement.setString(6, entity.getRole().getRoleValue());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void setIdForUpdate(PreparedStatement statement, Person entity) throws DaoException {
        try {
            statement.setLong(7, entity.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
