package my.sky.web_application_structure.repository;


import java.util.Map;

public interface IdRepository<T> {

    boolean add(T t);

    T findById(Long id);

    boolean update(Long id, T t);

    boolean delete(Long id);

    Map<Long, T> viewAll();
}