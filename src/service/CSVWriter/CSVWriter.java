package service.CSVWriter;

import model.Client;
import java.util.List;

public interface CSVWriter<T> {
    void save(List<T> objs, String filename);
}
