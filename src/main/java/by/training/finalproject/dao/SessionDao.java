package by.training.finalproject.dao;

import by.training.finalproject.entity.Session;

import java.util.List;

public interface SessionDao {
    long addSession(Session session) throws DaoException;
    void deleteSession(Session session) throws DaoException;
    void deleteSession(long idSession) throws DaoException;
    void updateSession(Session session) throws DaoException;
    List<Session> readAllSessions() throws DaoException;
    Session readById(long id) throws DaoException;
}
