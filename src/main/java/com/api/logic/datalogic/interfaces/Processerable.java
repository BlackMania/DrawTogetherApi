package com.api.logic.datalogic.interfaces;

import java.util.List;

public interface Processerable<T> {
    List<T> getAll();
    void create(T entity);
    T getById(int id);
    void delete(T entity);
}
