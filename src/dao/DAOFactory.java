package dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ������� �������� ��� ������ � ����� ������
**/

public interface DAOFactory {

    /** ���������� ����������� � ���� ������ */
    public Connection getConnection() throws SQLException;

    /** ���������� ������ ��� ���������� ������������� ���������� ������� Taxi */
    public TaxiDAO getTaxiDao(Connection connection);

    /** ���������� ������ ��� ���������� ������������� ���������� ������� Customer */
    public CustomerDAO getCustomerDao(Connection connection);

    /** ���������� ������ ��� ���������� ������������� ���������� ������� Customer */
    public OrderDAO getOrderDao(Connection connection);

}

