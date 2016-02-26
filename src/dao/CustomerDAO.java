package dao;

import entity.Customer;

import java.sql.SQLException;
import java.util.List;

/** ������ ��� ���������� ������������� ���������� ������� Customer */
public interface CustomerDAO {

    /** ������� ����� ������ � ��������������� �� ������ */
    public Customer create();

    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null */
    public Customer read(int key);

    /** ��������� ��������� ������� Customer � ���� ������ */
    public void update(Customer customer);

    /** ������� ������ �� ������� �� ���� ������ */
    public void delete(Customer customer);

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ */
    public List<Customer> getAll() throws SQLException;
}