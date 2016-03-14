package gui;

import java.sql.SQLException;

/*
*Класс для запуска оконного интерфейса
 */
public class GuiTest {
    public static void main(String[] args) {
        CustomerFrame cf = null;
        try {
            cf = new CustomerFrame();
        } catch (SQLException e) {
            e.printStackTrace();
}
        cf.setVisible(true);
        }
        }