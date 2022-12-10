package my.sky.web_application_structure.repository;


import java.util.Map;

public interface IdRepository<T> {

    Map<Long, T> add(Long id, T t);

    T findById(Long id);
}