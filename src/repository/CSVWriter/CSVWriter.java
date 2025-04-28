package repository.CSVWriter;

import java.util.List;

public interface CSVWriter<T> {
    void save(List<T> objs, String filename);
}
