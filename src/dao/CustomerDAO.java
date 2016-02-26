package dao;

import entity.Customer;

import java.sql.SQLException;
import java.util.List;

/** Объект для управления персистентным состоянием объекта Customer */
public interface CustomerDAO {

    /** Создает новую запись и соответствующий ей объект */
    public Customer create();

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public Customer read(int key);

    /** Сохраняет состояние объекта Customer в базе данных */
    public void update(Customer customer);

    /** Удаляет запись об объекте из базы данных */
    public void delete(Customer customer);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Customer> getAll() throws SQLException;
}