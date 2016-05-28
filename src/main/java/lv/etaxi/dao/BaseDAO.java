package lv.etaxi.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aleks on 19.04.2016.
 */
public interface BaseDAO<T> {

        long create(T entity) throws SQLException;

        void update(T entity) throws SQLException;

        void delete(T entity) throws SQLException;

        T getById(long id) throws SQLException;

        List<T> getAll() throws SQLException;

        T getByLogin(String login) throws SQLException;

}