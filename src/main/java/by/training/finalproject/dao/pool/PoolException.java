package by.training.finalproject.dao.pool;

public class PoolException extends Exception{
    public PoolException() {
        super();
    }
    public PoolException(String message) {
        super(message);
    }
    public PoolException(Throwable throwable) {
        super(throwable);
    }
    public PoolException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
