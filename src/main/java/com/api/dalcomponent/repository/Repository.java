package com.api.dalcomponent.repository;

import com.api.dalcomponent.IDBContext;
import com.api.dalcomponent.interfaces.IRepository;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public abstract class Repository<T> implements IRepository<T> {
    protected Dao<T, Integer> dao;
    protected Class<T> clazz;
    private IDBContext context;

    @Inject
    public Repository(Class<T> clazz, IDBContext dbContext) {
        this.clazz = clazz;
        this.context = dbContext;
        try {
            this.dao = DaoManager.createDao(context.getConnectionSource(), this.clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean save(T entity) {
        boolean success = false;
        try {
            dao.create(entity);
            success = true;
        } catch (SQLException exc) {
            exc.printStackTrace();
            success = false;
        }
        return success;
    }

    @Override
    public List<T> getAll() {
        List<T> data = new ArrayList<>();
        try {
            data = dao.queryForAll();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return data;
    }


    @Override
    public T findById(int id) {
        T data = null;
        try {
            data = dao.queryForId(id);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return data;
    }

    @Override
    public void delete(T entity) {
        try {
            dao.delete(entity);
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }
}
