package by.training.finalproject.dao;

import by.training.finalproject.entity.Master;
import by.training.finalproject.entity.Person;
import by.training.finalproject.entity.Role;
import by.training.finalproject.entity.Service;

import java.util.List;

public interface MasterDao {
    //колл PersonDao.addPerson();
    long addMaster(Master master) throws DaoException;
    void deleteMaster(Master master) throws DaoException;
    void deleteMaster(long idMaster) throws DaoException;
    void updateMaster(Master master) throws DaoException;
    List<Master> readAllMasters() throws DaoException;
    //можно сделать хранимой процедурой, где сначала получаешь айдишник услуги, а по нему потом список мастеров
    List<Master> readMastersByService(Service service);
    Master readById(long id) throws DaoException;
    boolean signIn(String email, String passwordHash) throws DaoException;
    boolean register(Master person) throws DaoException;
}
