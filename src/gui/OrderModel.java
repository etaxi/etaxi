package gui;

import entity.Order;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class OrderModel extends AbstractTableModel
{
    // Список загловков для колонок в таблице
    private static final String[] headers = {"ID", "Имя", "Фамилия", "Email", "Телефон"};

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
    
/*
    // Здесь мы храним список контактов, которые будем отображать в таблице
    private final List<Contact> contacts;

    public OrderModel(List<Contact> contacts) {
        this.contacts = contacts;
    }
    
    @Override
    // Получить количество строк в таблице - у нас это размер коллекции
    public int getRowCount() {
        return contacts.size();
    }

    @Override
    // Получить количество столбцов - их у нас стольк же, сколько полей
    // у класса Contact - всего пять
    public int getColumnCount() {
        return 5;
    }

    @Override
    // Вернуть загловок колонки - мы его берем из массива headers
    public String getColumnName(int col) {
        return headers[col];
    }
    
    @Override
    // Получить объект для тображения в кокнретной ячейке таблицы
    // В данном случае мы отдаем строковое представление поля
    public Object getValueAt(int row, int col) {
        Contact contact = contacts.get(row);
        // В зависимости от номера колонки возвращаем то или иное поле контакта
        switch (col) {
            case 0:
                return contact.getContactId().toString();
            case 1:
                return contact.getFirstName();
            case 2:
                return contact.getLastName();
            case 3:
                return contact.getEmail();
            default:
                return contact.getPhone();
        }
    }
*/
}

