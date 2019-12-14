package by.training.finalproject.dao.sql;

import by.training.finalproject.dao.DaoException;
import by.training.finalproject.dao.SessionDao;
import by.training.finalproject.entity.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionDaoImpl extends BaseDaoImpl<Session> implements SessionDao {
    private static final String INSERT_QUERY = "INSERT INTO `session` (`id_Client`, `id_Master`, `id_Service`, `session_date`, `session_time`) VALUES (?,?,?,?,?);";
    private static final String SELECT_ALL = "SELECT `id`, `id_Client`, `id_Master`, `id_Service`, `session_date`, `session_time` FROM `session` ORDER BY `id`;";
    private static final String UPDATE = "UPDATE `session` SET `id_Client`=?, `id_Master`=?, `id_Service`=?, `session_date`=?, `session_time`=? WHERE `id` = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM `session` WHERE `id`=?;";
    private static final String SELECT_BY_ID = "SELECT `id`, `id_Client`, `id_Master`, `id_Service`, `session_date`, `session_time` FROM `session` WHERE `id`=?;";
    @Override
    public long addSession(Session session) throws DaoException {
        return defaultInsert(INSERT_QUERY, session);
    }

    @Override
    public void deleteSession(Session session) throws DaoException {
        defaultDelete(DELETE_BY_ID, session.getId());
    }

    @Override
    public void deleteSession(long idSession) throws DaoException {
        defaultDelete(DELETE_BY_ID, idSession);
    }

    @Override
    public void updateSession(Session session) throws DaoException {
        defaultUpdate(UPDATE, session);
    }

    @Override
    public List<Session> readAllSessions() throws DaoException {
        return defaultRead(SELECT_ALL, new String[0]);
    }

    @Override
    public Session readById(long id) throws DaoException {
        try {
            return defaultRead(SELECT_BY_ID, new String[]{String.valueOf(id)}).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new DaoException("There is no session with such identifier");
        }
    }

    @Override
    protected List<Session> fillList(ResultSet resultSet) throws DaoException {
        List<Session> result = new ArrayList<>();
        Session session;
        try {
            while (resultSet.next()) {
                session = new Session();
                session.setId(resultSet.getLong("id"));
                session.setIdClient(resultSet.getLong("id_Client"));
                session.setIdMaster(resultSet.getLong("id_Master"));
                session.setIdService(resultSet.getLong("id_Service"));
                session.setDate(resultSet.getDate("session_date"));
                session.setTime(resultSet.getTime("session_time"));
                result.add(session);
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void setAllFields(PreparedStatement statement, Session entity) throws DaoException {
        try {
            statement.setLong(1, entity.getIdClient());
            statement.setLong(2, entity.getIdMaster());
            statement.setLong(3, entity.getIdService());
            statement.setDate(4, entity.getDate());
            statement.setTime(5, entity.getTime());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void setIdForUpdate(PreparedStatement statement, Session entity) throws DaoException {
        try {
            statement.setLong(6, entity.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
