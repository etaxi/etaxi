package dao.jdbc;

/** Проект etaxi
 * * класс для определения исключений
 */

public class DBException extends Exception {
    public DBException(Throwable throwable) {
        super(throwable);
    }
}
