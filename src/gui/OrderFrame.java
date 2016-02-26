package gui;

import business.OrderManager;
import entity.Order;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class OrderFrame extends JFrame implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {

    }
/*    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";

    private final OrderManager contactManager = new OrderManager();
    private final JTable contactTable = new JTable();

    // В конструкторе мы создаем нужные элементы
    public OrderFrame() {
        // Выставляем у таблицы свойство, которое позволяет выделить
        // ТОЛЬКО одну строку в таблице
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

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
        add(new JScrollPane(contactTable), BorderLayout.CENTER);

        // выставляем координаты формы
        setBounds(100, 200, 900, 400);
        // При закрытии формы заканчиваем работу приложения
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Загружаем контакты
        loadContact();
    }

    // Метод создает кнопку с заданными харктеристиками - заголовок и действие
    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        // Создаем кнопку с заданным загловком
        JButton button = new JButton(title);
        // Действие будетп роверяться в обработчике и мы будем знать, какую 
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
                loadContact();
                break;
            // Добавление записи
            case ADD:
                addContact();
                break;
            // Исправление записи
            case EDIT:
                editContact();
                break;
            // Удаление записи
            case DELETE:
                deleteContact();
                break;
        }
    }

    // Загрухить список контактов
    private void loadContact() {
        // Обращаемся к классу для загрузки списка контактов
        List<Order> order = contactManager.findOrder();
        // Создаем модель, которой передаем полученный список
        OrderModel cm = new OrderModel(order);
        // Передаем нашу модель таблице - и она может ее отображать
        contactTable.setModel(cm);
    }

    // Добавление контакта
    private void addContact() {
        // Создаем диалог для ввода данных
        EditOrderDialog ecd = new EditOrderDialog();
        // Обрабатываем закрытие диалога
        saveContact(ecd);
    }

    // Редактирование контакта
    private void editContact() {
        // Получаем выделеннуб строку
        int sr = contactTable.getSelectedRow();
        // если строка выделена - можно ее редактировать
        if (sr != -1) {
            // Получаем ID контакта
            Long id = Long.parseLong(contactTable.getModel().getValueAt(sr, 0).toString());
            // получаем данные контакта по его ID
            Contact cnt = contactManager.getContact(id);
            // Создаем диалог для ввода данных и передаем туда контакт
            EditOrderDialog ecd = new EditOrderDialog(contactManager.getContact(id));
            // Обрабатываем закрытие диалога
            saveContact(ecd);
        } else {
            // Если строка не выделена - сообщаем об этом
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для редактирования");
        }
    }

    // Удаление контакта
    private void deleteContact() {
        // Получаем выделеннуб строку
        int sr = contactTable.getSelectedRow();
        if (sr != -1) {
            // Получаем ID контакта
            Long id = Long.parseLong(contactTable.getModel().getValueAt(sr, 0).toString());
            // Удаляем контакт
            contactManager.deleteContact(id);
            // перегружаем список контактов
            loadContact();
        } else {
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для удаления");
        }
    }

    // Общий метод для добавления и изменения контакта
    private void saveContact(EditOrderDialog ecd) {
        // Если мы нажали кнопку SAVE
        if (ecd.isSave()) {
            // Получаем контакт из диалогового окна
            Contact cnt = ecd.getContact();
            if (cnt.getContactId() != null) {
                // Если ID у контакта есть, то мы его обновляем
                contactManager.updateContact(cnt);
            } else {
                // Если у контакта нет ID - значит он новый и мы его добавляем
                contactManager.addContact(cnt);
            }
            loadContact();
        }
    }*/
}

