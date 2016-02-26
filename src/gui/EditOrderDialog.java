package gui;

import entity.Order;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class EditOrderDialog extends JDialog implements ActionListener
{
    // ��������� ������
    private static final String SAVE = "SAVE";
    private static final String CANCEL = "CANCEL";

    @Override
    public void actionPerformed(ActionEvent e) {

    }

/*    // ������ �������
    private static final int PAD = 10;
    // ������ �����
    private static final int W_L = 100;
    //������ ���� ��� �����
    private static final int W_T = 300;
    // ������ ������
    private static final int W_B = 120;
    // ������ �������� - ����� ��� ����
    private static final int H_B = 25;

    // ���� ��� ����� �����
    private final JTextPane txtFirstName = new JTextPane();
    // ���� ��� ����� �������
    private final JTextPane txtLastName = new JTextPane();
    // ���� ��� ����� ��������
    private final JTextPane txtPhone = new JTextPane();
    // ���� ��� ����� E-mail
    private final JTextPane txtEmail = new JTextPane();

    // ���� ��� �������� ID ��������, ���� �� ���������� �������������
    // ���� ��� ����� ������� - cjntactId == null
    private Long contactId = null;
    // ���� �� ���������� ��������� ����� �������� �������
    private boolean save = false;

    public EditOrderDialog() {
        this(null);
    }

    public EditOrderDialog(Contact contact) {
        // ������� layout - ����� ������������ ���������� ����������
        setLayout(null);

        // ����������� ����� � ���� ��� �����
        buildFields();
        // ���� ��� �������� ������� - ��������� ���� �����
        initFields(contact);
        // ����������� ������
        buildButtons();

        // ������ � ��������� ������ - ������ �� �������
        setModal(true);
        // ��������� ��������� ��������
        setResizable(false);
        // ���������� ������� �����
        setBounds(300, 300, 450, 200);
        // ������ ����� �������
        setVisible(true);
    }

    // ��������� ����� � ���� ����� �� �����
    private void buildFields() {
        // ����� ����� � ���� ��� �����
        JLabel lblFirstName = new JLabel("���:");
        // ������������ ������ � ������ �������
        lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
        // ���������� ���������� �����
        lblFirstName.setBounds(new Rectangle(PAD, 0 * H_B + PAD, W_L, H_B));
        // ������ ����� �� �����
        add(lblFirstName);
        // ���������� ���������� ����
        txtFirstName.setBounds(new Rectangle(W_L + 2 * PAD, 0 * H_B + PAD, W_T, H_B));
        // ������ "������" ��� ����
        txtFirstName.setBorder(BorderFactory.createEtchedBorder());
        // ������ ���� �� �����
        add(txtFirstName);

        // ����� ����� � ���� ��� �������
        JLabel lblLastName = new JLabel("�������:");
        lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLastName.setBounds(new Rectangle(PAD, 1 * H_B + PAD, W_L, H_B));
        add(lblLastName);
        txtLastName.setBounds(new Rectangle(W_L + 2 * PAD, 1 * H_B + PAD, W_T, H_B));
        txtLastName.setBorder(BorderFactory.createEtchedBorder());
        add(txtLastName);

        // ����� ����� � ���� ��� ��������
        JLabel lblPhone = new JLabel("�������:");
        lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPhone.setBounds(new Rectangle(PAD, 2 * H_B + PAD, W_L, H_B));
        add(lblPhone);
        txtPhone.setBounds(new Rectangle(W_L + 2 * PAD, 2 * H_B + PAD, W_T, H_B));
        txtPhone.setBorder(BorderFactory.createEtchedBorder());
        add(txtPhone);

        // ����� ����� � ���� ��� Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEmail.setBounds(new Rectangle(PAD, 3 * H_B + PAD, W_L, H_B));
        add(lblEmail);
        txtEmail.setBounds(new Rectangle(W_L + 2 * PAD, 3 * H_B + PAD, W_T, H_B));
        txtEmail.setBorder(BorderFactory.createEtchedBorder());
        add(txtEmail);
    }

    // ���� ��� ��������� ������� - ��������� ���� �� ��������
    private void initFields(Contact contact) {
        if (contact != null) {
            contactId = contact.getContactId();
            txtFirstName.setText(contact.getFirstName());
            txtLastName.setText(contact.getLastName());
            txtEmail.setText(contact.getEmail());
            txtPhone.setText(contact.getPhone());
        }
    }

    // ��������� ������ �� �����
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
    // ��������� ������� ������
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        // ���� ������ ������ SAVE (��������� ���������) - ���������� ����
        save = SAVE.equals(action);
        // ��������� �����
        setVisible(false);
    }

    // ���� �� ��������� ���������
    public boolean isSave() {
        return save;
    }

    // ������� ������� �� ���������� �����, ������� ����� ����� ��������
    public Contact getContact() {
        Contact contact = new Contact(contactId, txtFirstName.getText(),
                txtLastName.getText(), txtPhone.getText(), txtEmail.getText());
        return contact;
    }*/
}

