package my.sky.web_application_structure.repository;


import java.util.Map;

public interface IdRepository<T> {

    Map<Long, T> add(T t);

    T findById(Long id);

    Map<Long, T> update(Long id, T t);

    void delete(Long id);

    Map<Long, T> viewAll();
}