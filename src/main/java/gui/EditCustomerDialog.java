package gui;

import dataSets.CustomerDataSet;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;


public class EditCustomerDialog extends JDialog implements ActionListener
{
    // Заголовки кнопок
    private static final String SAVE = "SAVE";
    private static final String CANCEL = "CANCEL";

    // Размер отступа
    private static final int PAD = 10;
    // Ширина метки
    private static final int W_L = 100;
    //Ширина поля для ввода
    private static final int W_T = 300;
    // Ширина кнопки
    private static final int W_B = 120;
    // высота элемента - общая для всех
    private static final int H_B = 25;

    // Поле для ввода Имени
    private final JTextPane txtName = new JTextPane();
    // Поле для ввода Логин
    private final JTextPane txtLogin = new JTextPane();
    // Поле для ввода Пароль
    private final JTextPane txtPass = new JTextPane();
    // Поле для ввода Телефон
    private final JTextPane txtPhone = new JTextPane();

    // Поле для хранения ID клиента, если мы собираемся редактировать
    // Если это новый клиент - custtomerId == 0
    private Long customerId = (long) 0;
    // Надо ли записывать изменения после закрытия диалога
    private boolean save = false;

    public EditCustomerDialog() {
        this(null);
    }

    public EditCustomerDialog(CustomerDataSet customer) {
        // Убираем layout - будем использовать абсолютные координаты
        setLayout(null);

        // Выстраиваем метки и поля для ввода
        buildFields();
        // Если нам передали клиента - заполняем поля формы
        initFields(customer);
        // выстариваем кнопки
        buildButtons();

        // Диалог в модальном режиме - только он активен
        setModal(true);
        // Запрещаем изменение размеров
        setResizable(false);
        // Выставляем размеры формы
        setBounds(300, 300, 450, 200);
        // Делаем форму видимой
        setVisible(true);
    }

    // Размещаем метки и поля ввода на форме
    private void buildFields() {
        // Набор метки и поля для Имени
        JLabel lblName = new JLabel("Имя:");
        // Выравнивание текста с правой стороны
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        // Выставляем координаты метки
        lblName.setBounds(new Rectangle(PAD, 0 * H_B + PAD, W_L, H_B));
        // Кладем метку на форму
        add(lblName);
        // Выставляем координаты поля
        txtName.setBounds(new Rectangle(W_L + 2 * PAD, 0 * H_B + PAD, W_T, H_B));
        // Делаем "бордюр" для поля
        txtName.setBorder(BorderFactory.createEtchedBorder());
        // Кладем поле на форму
        add(txtName);

        // Набор метки и поля для Логин
        JLabel lblLogin = new JLabel("Логин:");
        lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLogin.setBounds(new Rectangle(PAD, 1 * H_B + PAD, W_L, H_B));
        add(lblLogin);
        txtLogin.setBounds(new Rectangle(W_L + 2 * PAD, 1 * H_B + PAD, W_T, H_B));
        txtLogin.setBorder(BorderFactory.createEtchedBorder());
        add(txtLogin);

        // Набор метки и поля для Пароль
        JLabel lblPass = new JLabel("Пароль:");
        lblPass.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPass.setBounds(new Rectangle(PAD, 3 * H_B + PAD, W_L, H_B));
        add(lblPass);
        txtPass.setBounds(new Rectangle(W_L + 2 * PAD, 3 * H_B + PAD, W_T, H_B));
        txtPass.setBorder(BorderFactory.createEtchedBorder());
        add(txtPass);

        // Набор метки и поля для Телефона
        JLabel lblPhone = new JLabel("Телефон:");
        lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPhone.setBounds(new Rectangle(PAD, 2 * H_B + PAD, W_L, H_B));
        add(lblPhone);
        txtPhone.setBounds(new Rectangle(W_L + 2 * PAD, 2 * H_B + PAD, W_T, H_B));
        txtPhone.setBorder(BorderFactory.createEtchedBorder());
        add(txtPhone);
   }

    // Если нам епередали клиента - заполняем поля из клиента
    private void initFields(CustomerDataSet customer) {
        if (customer != null) {
            customerId = customer.getCustomerId();
            txtName.setText(customer.getName());
            txtLogin.setText(customer.getLogin());
            txtPass.setText(customer.getPassword());
            txtPhone.setText(customer.getPhone());
        }
    }

    // Размещаем кнопки на форме
    private void buildButtons() {
        JButton btnSave = new JButton("SAVE");
        btnSave.setActionCommand(SAVE);
        btnSave.addActionListener(this);
        btnSave.setBounds(new Rectangle(PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnSave);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setActionCommand(CANCEL);
        btnCancel.addActionListener(this);
        btnCancel.setBounds(new Rectangle(W_B + 2 * PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnCancel);
    }

    @Override
    // Обработка нажатий кнопок
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        // Если нажали кнопку SAVE (сохранить изменения) - запоминаем этой
        save = SAVE.equals(action);
        // Закрываем форму
        setVisible(false);
    }

    // Надо ли сохранять изменения
    public boolean isSave() {
        return save;
    }

    // Создаем клиента из заполенных полей, который можно будет записать
    public CustomerDataSet getCustomer() {
        CustomerDataSet customer = new CustomerDataSet(customerId, txtName.getText(), txtPhone.getText() ,txtLogin.getText(), txtPass.getText());
        return customer;
    }
}

