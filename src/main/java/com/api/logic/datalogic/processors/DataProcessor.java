package com.api.logic.datalogic.processors;

import com.api.dalcomponent.interfaces.IRepository;
import com.api.logic.datalogic.interfaces.Processerable;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DataProcessor<T> implements Processerable<T> {

    protected IRepository<T> repo;

    public DataProcessor(IRepository<T> repo) {
        this.repo = repo;
    }

    @Override
    public List<T> getAll() {
        List<T> data = new ArrayList<>();
        try {
            data = repo.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void create(T entity) {
        try {
            repo.save(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getById(int id) {
        T data = null;
        try {
            data = repo.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void delete(T entity) {
        try {
            repo.delete(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
