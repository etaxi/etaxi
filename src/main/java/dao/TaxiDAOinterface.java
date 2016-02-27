package dao;

import java.sql.SQLException;

/** Проект etaxi
 * Интерфейс для реализации управления объектами класса TaxiDataSet
 */
public interface TaxiDAOinterface {

/*
    // Добавление контакта - возвращает ID добавленного контакта
    public Long addContact(Contact contact);
    // Редактирование контакта
    public void updateContact(Contact contact);
    // Удаление контакта по его ID
    public void deleteContact(Long contactId);
    // Получение контакта
    public Contact getContact(Long contactId);
    // Получение списка контактов
    public List<Contact> findContacts();

*/
    /** Создает таблицу в базе данных для хранения объектов класса TaxiDataSet */
    public void createTable() throws SQLException;

}

