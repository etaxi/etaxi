package gui;

import entity.Order;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class OrderModel extends AbstractTableModel
{
    // ������ ��������� ��� ������� � �������
    private static final String[] headers = {"ID", "���", "�������", "Email", "�������"};

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
    // ����� �� ������ ������ ���������, ������� ����� ���������� � �������
    private final List<Contact> contacts;

    public OrderModel(List<Contact> contacts) {
        this.contacts = contacts;
    }
    
    @Override
    // �������� ���������� ����� � ������� - � ��� ��� ������ ���������
    public int getRowCount() {
        return contacts.size();
    }

    @Override
    // �������� ���������� �������� - �� � ��� ������ ��, ������� �����
    // � ������ Contact - ����� ����
    public int getColumnCount() {
        return 5;
    }

    @Override
    // ������� �������� ������� - �� ��� ����� �� ������� headers
    public String getColumnName(int col) {
        return headers[col];
    }
    
    @Override
    // �������� ������ ��� ���������� � ���������� ������ �������
    // � ������ ������ �� ������ ��������� ������������� ����
    public Object getValueAt(int row, int col) {
        Contact contact = contacts.get(row);
        // � ����������� �� ������ ������� ���������� �� ��� ���� ���� ��������
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

