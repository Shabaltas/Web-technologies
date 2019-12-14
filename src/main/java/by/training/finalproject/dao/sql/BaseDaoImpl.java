package by.training.finalproject.dao.sql;

import java.sql.*;
import java.util.List;

import by.training.finalproject.dao.DaoException;
import by.training.finalproject.dao.pool.ConnectionPool;
import by.training.finalproject.dao.pool.PoolException;
import by.training.finalproject.entity.Entity;

public abstract class BaseDaoImpl<T extends Entity> {
    private static final String LAST_ID = "SELECT @@IDENTITY;";
    protected Connection connection;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    protected List<T> defaultRead(String query, String[] values) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                statement.setString(i+1, values[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            return fillList(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected abstract List<T> fillList(ResultSet resultSet) throws DaoException;

    protected void defaultUpdate(String query, T entity) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            setAllFields(statement, entity);
            setIdForUpdate(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected abstract void setAllFields(PreparedStatement statement, T entity) throws DaoException;
    protected abstract void setIdForUpdate(PreparedStatement statement, T entity) throws DaoException;

    protected void defaultDelete(String query, long id) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected long defaultInsert(String query, T entity) throws DaoException {
        try {
            String sql = "INSERT INTO `beauty_master` (`id`, `id_portfolio`, `experience`, `employment_date`, `status`) VALUES (4,null,2,'2019-01-23','working');";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //setAllFields(preparedStatement, entity);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
