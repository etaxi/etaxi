package lv.etaxi.business;

import lv.etaxi.entity.Taxi;

import java.sql.SQLException;

/**
 * C/** Проект etaxi
 * Интерфейс для реализации функций над объектами такси
 */
public interface TaxiManager {

    Taxi findByLogin(String login) throws SQLException;

    Taxi findById(long Id) throws SQLException;

    void create(Taxi taxi) throws SQLException;

    void update(Taxi taxi) throws SQLException;

    void deleteByObject(Taxi taxi) throws SQLException;

}
