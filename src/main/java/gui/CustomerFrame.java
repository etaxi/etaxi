package gui;

import dao.*;
import dataSets.CustomerDataSet;
import services.DBService;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;


public class CustomerFrame extends JFrame implements ActionListener {
        private static final String LOAD = "LOAD";
        private static final String ADD = "ADD";
        private static final String EDIT = "EDIT";
        private static final String DELETE = "DELETE";

    CustomerDAO customerDAO;
    final JTable customerTable = new JTable();

    // В конструкторе мы создаем нужные элементы
    public CustomerFrame() throws SQLException {

        DBService dbService = new DBService();
        Connection connection = dbService.getMysqlConnection();

        this.customerDAO = new CustomerDAOImpl(dbService.getConnection(), dbService.getDatabaseName());


        // Выставляем у таблицы свойство, которое позволяет выделить
        // ТОЛЬКО одну строку в таблице
        customerTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        // Используем layout GridBagLayout
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        // Каждый элемент является последним в строке
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        // Элемент раздвигается на весь размер ячейки
        gbc.fill = GridBagConstraints.BOTH;
        // Но имеет границы - слева, сверху и справа по 5. Снизу - 0
        gbc.insets = new Insets(5, 5, 0, 5);

        // Создаем панель для кнопок
        JPanel btnPanel = new JPanel();
        // усанавливаем у него layout
        btnPanel.setLayout(gridbag);
        // Создаем кнопки и укзаываем их загловок и ActionCommand
        btnPanel.add(createButton(gridbag, gbc, "Обновить", LOAD));
        btnPanel.add(createButton(gridbag, gbc, "Добавить", ADD));
        btnPanel.add(createButton(gridbag, gbc, "Исправить", EDIT));
        btnPanel.add(createButton(gridbag, gbc, "Удалить", DELETE));

        // Создаем панель для левой колокни с кнопками
        JPanel left = new JPanel();
        // Выставляем layout BorderLayout
        left.setLayout(new BorderLayout());
        // Кладем панель с кнопками в верхнюю часть
        left.add(btnPanel, BorderLayout.NORTH);
        // Кладем панель для левой колонки на форму слева - WEST
        add(left, BorderLayout.WEST);

        // Кладем панель со скролингом, внутри которой нахоится наша таблица
        // Теперь таблица может скроллироваться
        add(new JScrollPane(customerTable), BorderLayout.CENTER);

        // выставляем координаты формы
        setBounds(100, 200, 900, 400);
        // При закрытии формы заканчиваем работу приложения
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Загружаем контакты
        loadCustomers();
    }

    // Метод создает кнопку с заданными харктеристиками - заголовок и действие
    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        // Создаем кнопку с заданным загловком
        JButton button = new JButton(title);
        // Действие будет проверяться в обработчике и мы будем знать, какую
        // именно кнопку нажали
        button.setActionCommand(action);
        // Обработчиком события от кнопки являемся сама форма
        button.addActionListener(this);
        // Выставляем свойства для размещения для кнопки
        gridbag.setConstraints(button, gbc);
        return button;
    }

    @Override
    // Обработка нажатий кнопок
    public void actionPerformed(ActionEvent ae) {
        // Получаем команду - ActionCommand
        String action = ae.getActionCommand();
        // В зависимости от команды выполняем действия
        switch (action) {
            // Перегрузка данных
            case LOAD:
                try {
                    loadCustomers();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            // Добавление записи
            case ADD:
                try {
                    addCustomer();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            // Исправление записи
            case EDIT:
                try {
                    editCustomer();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            // Удаление записи
            case DELETE:
                try {
                    deleteContact();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    // Загрузить список кклиентов
    private void loadCustomers() throws SQLException {
        List<CustomerDataSet> listOfCustomers = customerDAO.getAll();
        // Создаем модель, которой передаем полученный список
        CustomerModel cm = new CustomerModel(listOfCustomers);
        // Передаем нашу модель таблице - и она может ее отображать
        customerTable.setModel(cm);
    }

    // Добавление клиента
    private void addCustomer() throws SQLException {
        // Создаем диалог для ввода данных
        EditCustomerDialog ecd = new EditCustomerDialog();
        // Обрабатываем закрытие диалога
        saveCustomer(ecd);
    }

    // Редактирование клиента
    private void editCustomer() throws SQLException {
        // Получаем выделенную строку
        int sr = customerTable.getSelectedRow();
        // если строка выделена - можно ее редактировать
        if (sr != -1) {
            // Получаем ID клиента
            Long id = Long.parseLong(customerTable.getModel().getValueAt(sr, 0).toString());
            // получаем данные кклиента по его ID
            try {
                CustomerDataSet cnt = customerDAO.getById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // Создаем диалог для ввода данных и передаем туда клиента
            EditCustomerDialog ecd = new EditCustomerDialog(customerDAO.getById(id));
            // Обрабатываем закрытие диалога
            saveCustomer(ecd);
        } else {
            // Если строка не выделена - сообщаем об этом
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для редактирования");
        }
    }

    // Удаление кклиента
    private void deleteContact() throws SQLException {
        // Получаем выделенную строку
        int sr = customerTable.getSelectedRow();
        if (sr != -1) {
            // Получаем ID клиента
            Long id = Long.parseLong(customerTable.getModel().getValueAt(sr, 0).toString());
            CustomerDataSet cnt = customerDAO.getById(id);
            // Удаляем клиента
            customerDAO.delete(cnt);
            // перегружаем список клиентов
            try {
                loadCustomers();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для удаления");
        }
    }

    // Общий метод для добавления и изменения клиентов
    private void saveCustomer(EditCustomerDialog ecd) throws SQLException {
        // Если мы нажали кнопку SAVE
        if (ecd.isSave()) {
            // Получаем клиента из диалогового окна
            CustomerDataSet cnt = ecd.getCustomer();
            if (cnt.getCustomerId() != null) {
                // Если ID у клиента есть, то мы его обновляем
                customerDAO.update(cnt);
            } else {
                // Если у клиента нет ID - значит он новый и мы его добавляем
                customerDAO.update(cnt);
            }
            loadCustomers();
        }
    }
}

