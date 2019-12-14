package by.training.finalproject.utilities;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JAXBSerializer<T> extends CustomSerializer<T> {
    @Override
    public void serialize(String filepath, T object) throws Exception{
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Path path = Paths.get(filepath);
        Files.deleteIfExists(path);
        Files.createFile(Paths.get(filepath));
        marshaller.marshal(object, new File(path.toString()));
    }

    @Override
    public T deserialize(String filepath, Class deserializedClass) throws Exception{
        JAXBContext context = JAXBContext.newInstance(deserializedClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        T result = (T) unmarshaller.unmarshal(new File(Paths.get(filepath).toString()));
        return result;
    }
}
