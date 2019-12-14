package by.training.finalproject.letbe;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {
    private final String url;
    private final String user;
    private final String password;
    private final int maxCount;
    private BlockingQueue<Connection> freeConnections;
    private volatile static ConnectionPool instance;
    private ConnectionPool(String url, String user, String password, int maxCount) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.maxCount = maxCount;
        this.freeConnections = new ArrayBlockingQueue<>(maxCount);
        try {
            loadDriver();
            fullQueue();
        } catch (SQLException e) {
            //выбросить из конструктора
            e.printStackTrace();
        }
    }
    public static ConnectionPool getInstance(String url, String user, String password, int maxCount) {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool(url, user, password, maxCount);
                }
            }
        }
        return instance;
    }
    private void loadDriver() throws SQLException {
        DriverManager.registerDriver(new Driver());
    }
    private void fullQueue() {
        for (int i = 0; i < maxCount; ) {
            try {
                freeConnections.add(DriverManager.getConnection(url, user, password));
                i++;
            } catch (SQLException e) {
                //это может быть бесконечно
                //может лучше пробросить исключение наверх(PoolException)
            }
        }
    }
    public Connection getConnection() {
        if (!freeConnections.isEmpty()) {
            try {
                return freeConnections.poll(2, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                return getConnection();
            }
        } else {
            return null;
        }
    }
    public void freeConnection(Connection connection) throws SQLException{
        try {
            if (freeConnections.size() < maxCount && !connection.isClosed()) {
                freeConnections.put(connection);
            }
        } catch (SQLException e) {
            //наверное выбросить лучше
        } catch (InterruptedException e) {
            freeConnection(connection);
        }
    }
}
