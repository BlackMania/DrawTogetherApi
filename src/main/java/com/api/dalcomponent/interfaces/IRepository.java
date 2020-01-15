package com.api.dalcomponent.interfaces;

import java.util.List;

public interface IRepository<T> {
    boolean save(T entity);
    List<T> getAll();
    T findById(int id);
    void delete(T entity);
}
