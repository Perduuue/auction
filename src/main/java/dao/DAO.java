package dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> get(int id);

    List<T> getAll();

    int add(T t);

    int update(T t);

    int delete(int id);
}
