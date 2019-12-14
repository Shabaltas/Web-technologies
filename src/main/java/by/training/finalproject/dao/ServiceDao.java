package by.training.finalproject.dao;

import by.training.finalproject.entity.Master;
import by.training.finalproject.entity.Service;
import by.training.finalproject.entity.Specialization;

import java.util.List;

public interface ServiceDao {
    //return id
    long addService(Service service) throws DaoException;
    void deleteService(Service service) throws DaoException;
    void deleteService(long idService) throws DaoException;
    void updateService(Service service) throws DaoException;
    List<Service> readAll() throws DaoException;
    List<Service> readServicesBySpec(Specialization specialization) throws DaoException;
    List<Service> readMasterServices(Master master) throws DaoException;
}
