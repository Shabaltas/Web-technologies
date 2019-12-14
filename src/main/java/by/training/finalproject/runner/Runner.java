package by.training.finalproject.runner;

import by.training.finalproject.dao.DaoException;
import by.training.finalproject.dao.pool.ConnectionPool;
import by.training.finalproject.dao.pool.PoolException;
import by.training.finalproject.dao.sql.ServiceDaoImpl;
import by.training.finalproject.entity.Service;
import by.training.finalproject.entity.Specialization;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

public class Runner {
    public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/beauty_saloon";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "root";
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT =0;

    public static void main(String[] arg) throws NoSuchAlgorithmException, UnsupportedEncodingException, PoolException, DaoException {
        ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        ServiceDaoImpl serviceDao = new ServiceDaoImpl();
        serviceDao.setConnection(ConnectionPool.getInstance().getConnection());
        Service service = new Service();
        service.setIdSpecialization(2);
        service.setCost(BigDecimal.valueOf(45.5));
        service.setServiceName("addd");
        service.setDuration(75);
        //service.setId(6);
        //serviceDao.deleteService(11);
        //serviceDao.updateService(service);
        //System.out.println(serviceDao.addService(service));
        Specialization specialization = new Specialization();
        //specialization.setId(3);
        serviceDao.addService(service);
        //serviceDao.readAll().forEach(service1 -> System.out.println(service1));
    }
}
