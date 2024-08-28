package ps.demo.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ProcessingHandler<T> {

    List<String> validate(List<T> data);

    List<T> parse(InputStream input) throws IOException;

}
