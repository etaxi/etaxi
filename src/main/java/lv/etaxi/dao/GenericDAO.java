package lv.etaxi.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Aleks on 19.04.2016.
 */
public interface GenericDAO<T> {

        void create(T type) throws DBException;

        T getById(Long id) throws DBException;

        void delete(Long id) throws DBException;

        long update(T type) throws DBException, SQLException;

        List<T> getAll() throws DBException, SQLException;

        }