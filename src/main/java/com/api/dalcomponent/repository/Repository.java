package com.api.dalcomponent.repository;

import com.api.dalcomponent.IDBContext;
import com.api.dalcomponent.interfaces.IRepository;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public abstract class Repository<T> implements IRepository<T> {
    protected Dao<T, Integer> dao;
    protected Class<T> clazz;

    @Inject
    public Repository(Class<T> clazz, IDBContext dbContext) {
        this.clazz = clazz;
        try {
            this.dao =  DaoManager.createDao(dbContext.getConnectionSource(), this.clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(T entity) throws SQLException {
        dao.create(entity);
    }

    @Override
    public List<T> getAll() throws SQLException {
        return dao.queryForAll();
    }

    // TODO
    // Find by id fixen
    @Override
    public T findById(int id) throws SQLException {
        return null;
    }

    @Override
    public void delete(T entity) throws SQLException {
        dao.delete(entity);
    }
}
