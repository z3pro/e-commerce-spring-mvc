package ra.service;

import java.util.List;

public interface IGenericService <T,E>{
    List<T> getAll();
    T findById(E id);
    void save(T t);
    void deleteById(E e);
}
