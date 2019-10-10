package com.api.dalcomponent.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T> {
    void save(T entity) throws SQLException;
    List<T> getAll() throws SQLException;
    T findById(int id) throws SQLException;
    void delete(T entity) throws SQLException;
}
