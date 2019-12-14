package by.training.finalproject.utilities;

public abstract class CustomSerializer<T> {
    public abstract void serialize(String filepath, T object) throws Exception;
    public abstract T deserialize(String filepath, Class deserializedClass) throws Exception;
}
