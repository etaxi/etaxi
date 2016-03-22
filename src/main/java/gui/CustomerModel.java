package gui;

import dataSets.CustomerDataSet;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class CustomerModel extends AbstractTableModel
{
    // Список заголовков для колонок в таблице
    private static final String[] headers = {"ID", "Имя", "Логин", "Пароль", "Телефон", "Тариф"};

    // Здесь мы храним список клиентов, которых будем отображать в таблице
    private final List<CustomerDataSet> customers;

    public CustomerModel(List<CustomerDataSet> customer) {
        this.customers = customer;
    }

    @Override
    // Получить количество строк в таблице - у нас это размер коллекции
    public int getRowCount() {
        return customers.size();
    }

    @Override
    // Получить количество столбцов - их у нас стольк же, сколько полей
    // у класса Customer - всего пять
    public int getColumnCount() {
        return 6;
    }

    @Override
    // Вернуть загловок колонки - мы его берем из массива headers
    public String getColumnName(int col) {
        return headers[col];
    }

    @Override
    // Получить объект для отображения в кокнретной ячейке таблицы
    // В данном случае мы отдаем строковое представление поля
    public Object getValueAt(int row, int col) {
        CustomerDataSet customer = customers.get(row);
        // В зависимости от номера колонки возвращаем то или иное поле клиента
        switch (col) {
            case 0:
                return customer.getCustomerId().toString();
            case 1:
                return customer.getName().toString();
            case 2:
                return customer.getPassword();
            default:
                return customer.getPhone();

        }
    }
}

