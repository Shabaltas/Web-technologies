package by.training.finalproject.dao;

import by.training.finalproject.entity.Specialization;

import java.util.List;

public interface SpecializationDao {
    long addSpecialization(Specialization specialization) throws DaoException;
    void deleteSpecialization(Specialization specialization) throws DaoException;
    void deleteSpecialization(long idSpecialization) throws DaoException;
    List<Specialization> readAllSpecializations() throws DaoException;
    void updateSpecialization(Specialization specialization) throws DaoException;
}
