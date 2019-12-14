package by.training.finalproject.dao.sql;

import by.training.finalproject.dao.DaoException;
import by.training.finalproject.dao.MasterDao;
import by.training.finalproject.entity.Master;
import by.training.finalproject.entity.Person;
import by.training.finalproject.entity.Role;
import by.training.finalproject.entity.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MasterDaoImpl {
    private static final String INSERT_QUERY = "INSERT INTO `beauty_master` (`id`, `id_portfolio`, `experience`, `employment_date`, `status`) VALUES (?,?,?,?,?);";
    private static final String SELECT_ALL = "SELECT `person`.`id`, `person`.`surname`, `person`.`name`, `person`.`email`, `person`.`passwordHash`, `person`.`phone`, " +
                                             "`beauty_master`.`id_portfolio`, `beauty_master`.`experience`, `beauty_master`.`employment_date`, `beauty_master`.`status` " +
                                             "FROM `beauty_master` INNER JOIN `person` ON `beauty_master`.`id` = `person`.`id` ORDER BY `person`.`id`;";

    private static final String UPDATE = "UPDATE `beauty_master`, `person` SET `surname`=?, `name`=?, `email`=?, `phone`=?, `passwordHash`=?, `id_portfolio`=?, `experience`=?, `employment_date`=?, `status`=? " +
                                         "WHERE `person`.`id` = ? AND `beauty_master`.`id` = `person`.`id`;";
    private static final String SELECT_BY_ID = "SELECT `person`.`id`, `person`.`surname`, `person`.`name`, `person`.`email`, `person`.`passwordHash`, `person`.`phone`, " +
            "`beauty_master`.`id_portfolio`, `beauty_master`.`experience`, `beauty_master`.`employment_date`, `beauty_master`.`status` " +
            "FROM `beauty_master` INNER JOIN `person` ON `beauty_master`.`id` = `person`.`id` WHERE `person`.`id`=?;";
    private static final String SELECT_BY_SERVICE = "SELECT `person`.`id`, `person`.`surname`, `person`.`name`, `person`.`email`, `person`.`passwordHash`, `person`.`phone`, " +
            "`beauty_master`.`id_portfolio`, `beauty_master`.`experience`, `beauty_master`.`employment_date`, `beauty_master`.`status` " +
            "FROM `beauty_master` INNER JOIN `person` ON `beauty_master`.`id` = `person`.`id` WHERE `beauty_master`.`id` = `master_service`.`id_Master` AND `master_service`.`id_Service`=? ORDER BY `id`;";

    //before INSERT_QUERY we should register person with Person.INSERT_QUERY

    //to delete from `beauty_master` call Person.deletePerson(master) - cascade deleting

    public long addMaster(Master master) throws DaoException {
        PersonDaoImpl dao = new PersonDaoImpl();
        long lastId;
        if ((lastId = dao.register(master)) > 0) {
            //defaultInsert(INSERT_QUERY, master);
        }
        return lastId;
    }
}
