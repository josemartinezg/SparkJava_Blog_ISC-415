package dao;

import java.util.List;
import java.util.Optional;
public interface Dao<T> {

    List<T> get(String username);

    List<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}
