package by.training.task4.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;

public class FileHandler {
    private static final String FILE_ATTRIBUTE = "fileXML";
    public void updateFile(String destination, HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart(FILE_ATTRIBUTE);
        File file = new File(destination);
        try(InputStream filecontent = filePart.getInputStream();
            OutputStream out = new FileOutputStream(file)) {
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        }
    }
}
