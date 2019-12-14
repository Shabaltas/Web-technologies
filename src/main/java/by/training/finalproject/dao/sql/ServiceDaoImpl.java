package by.training.finalproject.dao.sql;

import by.training.finalproject.dao.DaoException;
import by.training.finalproject.dao.ServiceDao;
import by.training.finalproject.entity.Master;
import by.training.finalproject.entity.Service;
import by.training.finalproject.entity.Specialization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl extends BaseDaoImpl<Service> implements ServiceDao {
    private static final String INSERT_QUERY = "INSERT INTO `service` (`id_Specialization`, `service`, `cost`, `duration`) VALUES (?, ?, ?, ?);";
    private static final String SELECT_ALL = "SELECT `id`, `id_Specialization`, `service`, `cost`, `duration` FROM `service` ORDER BY `id`;";
    private static final String UPDATE = "UPDATE `service` SET `id_Specialization`=?, `service`=?, `cost`=?, `duration`=? WHERE `id` = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM `service` WHERE `id`=?;";
    private static final String SELECT_BY_SPEC = "SELECT `id`, `id_Specialization`, `service`, `cost`, `duration` FROM `service` WHERE `id_Specialization`=? ORDER BY `id`;";
    private static final String SELECT_BY_MASTER = "SELECT `id`, `id_Specialization`, `service`, `cost`, `duration` FROM `service` " +
                                             "WHERE `service`.`id` = `master_service`.`id_Service` AND `master_service`.`id_Master`=? ORDER BY `id`;";

    @Override
    public long addService(Service service) throws DaoException {
        return defaultInsert(INSERT_QUERY, service);
    }

    @Override
    public void deleteService(Service service) throws DaoException {
        defaultDelete(DELETE_BY_ID, service.getId());
    }

    @Override
    public void deleteService(long idService) throws DaoException {
        defaultDelete(DELETE_BY_ID, idService);
    }

    @Override
    public void updateService(Service service) throws DaoException {
        defaultUpdate(UPDATE, service);
    }

    @Override
    public List<Service> readAll() throws DaoException {
        return defaultRead(SELECT_ALL, new String[0]);
    }

    @Override
    public List<Service> readServicesBySpec(Specialization specialization) throws DaoException {
        return defaultRead(SELECT_BY_SPEC, new String[]{String.valueOf(specialization.getId())});
    }

    @Override
    public List<Service> readMasterServices(Master master) throws DaoException {
        return defaultRead(SELECT_BY_MASTER, new String[]{String.valueOf(master.getId())});
    }

    @Override
    protected List<Service> fillList(ResultSet resultSet) throws DaoException {
        List<Service> result = new ArrayList<>();
        Service service;
        try {
            while (resultSet.next()) {
                service = new Service();
                service.setId(resultSet.getLong("id"));
                service.setIdSpecialization(resultSet.getLong("id_Specialization"));
                service.setServiceName(resultSet.getString("service"));
                service.setCost(resultSet.getBigDecimal("cost"));
                service.setDuration(resultSet.getInt("duration"));
                result.add(service);
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void setAllFields(PreparedStatement statement, Service entity) throws DaoException {
        try {
            statement.setLong(1, entity.getIdSpecialization());
            statement.setString(2, entity.getServiceName());
            statement.setBigDecimal(3, entity.getCost());
            statement.setInt(4, entity.getDuration());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void setIdForUpdate(PreparedStatement statement, Service entity) throws DaoException {
        try {
            statement.setLong(5, entity.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
