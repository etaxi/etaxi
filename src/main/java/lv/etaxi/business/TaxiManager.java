package lv.etaxi.business;

import lv.etaxi.entity.Taxi;

import java.sql.SQLException;

/**
 * C/** Проект etaxi
 * Интерфейс для реализации функций над объектами такси
 */
public interface TaxiManager {

    Taxi findTaxiByLogin(String login) throws SQLException;

    Taxi findTaxiById(long Id) throws SQLException;

    void createNewTaxi(Taxi taxi) throws SQLException;

    void updateTaxi(Taxi taxi) throws SQLException;

    void deleteTaxi(Taxi taxi) throws SQLException;

}
