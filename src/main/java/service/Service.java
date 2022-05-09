package service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    Optional<T> get(int id);

    List<T> getAll();

    boolean add(T t);

    boolean update(T t);

    boolean delete(int id);
}
