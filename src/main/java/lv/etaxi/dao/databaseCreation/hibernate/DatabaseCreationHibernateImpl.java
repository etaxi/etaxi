package lv.etaxi.dao.databaseCreation.hibernate;

import lv.etaxi.dao.databaseCreation.DatabaseCreation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by D.Lazorkin on 30.04.2016.
 */
@Component
public class DatabaseCreationHibernateImpl implements DatabaseCreation {

    @Autowired
    private SessionFactory sessionFactory;

    public void createTableForCustomers() throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS customers(" +
                "   Id bigint(9) NOT NULL auto_increment," +
                "   name varchar(256)," +
                "   phone varchar(256)," +
                "   password varchar(256)," +
                "   PRIMARY KEY (Id)" +
                "   );").executeUpdate();
    }

    public void createTableForAdmins() throws SQLException {

            Session session = sessionFactory.getCurrentSession();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS admins(" +
                    "   Id bigint(9) NOT NULL auto_increment," +
                    "   name varchar(256)," +
                    "   login varchar(256)," +
                    "   password varchar(256)," +
                    "   PRIMARY KEY (Id)" +
                    "   );").executeUpdate();
        }

    public void createTableForOrders() throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS orders (" +
                "  Id bigint(9) NOT NULL auto_increment," +
                "  customerId bigint(9)," +
                "  datetime datetime," +
                "  ordereddatetime datetime," +
                "  orderStatus text," +
                "  fromAdress text," +
                "  toAdress text," +
                "  taxiId bigint(9)," +
                "  distance double," +
                "  price double," +
                "  rate int(2)," +
                "  feedback text," +
                "  PRIMARY KEY (Id)" +
                ");").executeUpdate();
    }

    public void createTableForTaxi() throws SQLException {

            Session session = sessionFactory.getCurrentSession();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS taxis (" +
                    "  Id bigint(9) NOT NULL auto_increment," +
                    "  name varchar(256)," +
                    "  phone varchar(256)," +
                    "  taxiStatus int(1)," +
                    "  location varchar(256)," +
                    "  car varchar(256)," +
                    "  login varchar(256)," +
                    "  password varchar(256)," +
                    "  rating double," +
                    "  PRIMARY KEY  (Id)" +
                    ");").executeUpdate();
        }

    public void createDatabase(boolean dropDatabase) throws SQLException {



    }
}
