package lv.etaxi.business;

import lv.etaxi.entity.Taxi;

import java.sql.SQLException;

/**
 * C/** Проект etaxi
 * Интерфейс для реализации функций над объектами такси
 */
public interface TaxiManager {

    void create(Taxi taxi) throws SQLException;

    void delete(Taxi taxi) throws SQLException;

    void update(Taxi taxi) throws SQLException;

    Taxi findById(long Id) throws SQLException;

    Taxi findByLogin(String login) throws SQLException;
}
