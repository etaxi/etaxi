package dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Функциональный интерфейс для возврата результата работы объекта Executor
 */

@FunctionalInterface
public interface ResultHandler<T> {

    T handle(ResultSet resultSet) throws SQLException;

}
