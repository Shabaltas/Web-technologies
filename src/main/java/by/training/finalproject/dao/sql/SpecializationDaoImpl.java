package by.training.finalproject.dao.sql;

import by.training.finalproject.dao.DaoException;
import by.training.finalproject.dao.SpecializationDao;
import by.training.finalproject.entity.Specialization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecializationDaoImpl extends BaseDaoImpl<Specialization> implements SpecializationDao {
    private static final String INSERT_QUERY = "INSERT INTO `specialization` (`specialization`) VALUES (?);";
    private static final String SELECT_ALL = "SELECT `id`, `specialization` FROM `specialization` ORDER BY `id`;";
    private static final String UPDATE = "UPDATE `specialization` SET `specialization`=? WHERE `id` = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM `specialization` WHERE `id`=?;";

    @Override
    public long addSpecialization(Specialization specialization) throws DaoException {
        return defaultInsert(INSERT_QUERY, specialization);
    }

    @Override
    public void deleteSpecialization(Specialization specialization) throws DaoException {
        defaultDelete(DELETE_BY_ID, specialization.getId());
    }

    @Override
    public void deleteSpecialization(long idSpecialization) throws DaoException {
        defaultDelete(DELETE_BY_ID, idSpecialization);
    }

    @Override
    public List<Specialization> readAllSpecializations() throws DaoException {
        return defaultRead(SELECT_ALL, new String[0]);
    }

    @Override
    public void updateSpecialization(Specialization specialization) throws DaoException {
        defaultUpdate(UPDATE, specialization);
    }

    @Override
    protected List<Specialization> fillList(ResultSet resultSet) throws DaoException {
        List<Specialization> result = new ArrayList<>();
        Specialization specialization;
        try {
            while (resultSet.next()) {
                specialization = new Specialization();
                specialization.setId(resultSet.getLong("id"));
                specialization.setTitle(resultSet.getString("specialization"));
                result.add(specialization);
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void setAllFields(PreparedStatement statement, Specialization entity) throws DaoException {
        try {
            statement.setString(1, entity.getTitle());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void setIdForUpdate(PreparedStatement statement, Specialization entity) throws DaoException {
        try {
            statement.setLong(2, entity.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
