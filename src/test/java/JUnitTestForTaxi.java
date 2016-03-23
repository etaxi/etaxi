import dao.TaxiDAO;
import dao.jdbc.TaxiDAOImpl;
import entity.Taxi;
import org.junit.Test;
import dao.jdbc.DBService;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** Проект etaxi
 * JUnit тесты для проекта etaxi (design patterns "Object Mother" and "Test Data Builder")
 * */

class TaxiBuilder {

    public static final Long   DEFAULT_ID = (long) 0;
    public static final String DEFAULT_NAME = "Fernando Alonso Díaz";
    public static final String DEFAULT_CAR = "Ford Mondeo 1.8L";
    public static final String DEFAULT_PHONE = "(+371) 000000000";
    public static final String DEFAULT_LOGIN = "login";
    public static final String DEFAULT_PASSWORD = "password";

    private Long id = DEFAULT_ID;
    private String login = DEFAULT_LOGIN;
    private String car = DEFAULT_CAR;
    private String password = DEFAULT_PASSWORD;
    private String phone = DEFAULT_PHONE;
    private String name = DEFAULT_NAME;

    private TaxiBuilder() {}

    public static TaxiBuilder aTaxi() {
        return new TaxiBuilder();
    }

    public TaxiBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public TaxiBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TaxiBuilder withCar(String car) {
        this.car = car;
        return this;
    }

    public TaxiBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public TaxiBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public TaxiBuilder withNoLogin() {
        this.login = null;
        return this;
    }

    public TaxiBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public TaxiBuilder withNoPassword() {
        this.password = null;
        return this;
    }


    public TaxiBuilder but() {
        return TaxiBuilder
                .aTaxi()
                .withName(name)
                .withCar(car)
                .withPhone(phone)
                .withPassword(password)
                .withLogin(login);
    }

    public Taxi build() {
        return new Taxi(id, name, car, phone, login, password);
    }
}

public class JUnitTestForTaxi {

    public TaxiDAO aTaxiDAO() {

        DBService dbService = new DBService();
        return (new TaxiDAOImpl(dbService.getConnection(), dbService.getDatabaseName()));
    }

    @Test
    public void testNewTaxiRecord() throws SQLException {

        TaxiBuilder taxiBuilder = TaxiBuilder.aTaxi()
                .withName("Jenson Alexander Lyons Button")
                .withLogin("Lyons")
                .withPassword("lybutton");

        Taxi taxi = taxiBuilder.build();
        long newTaxiID = aTaxiDAO().update(taxi);
    }


    @Test
    public void testNewTaxisRecord() throws SQLException {

        TaxiDAO taxiDAO = aTaxiDAO();

        TaxiBuilder taxiBuilder = TaxiBuilder.aTaxi()
                .withName("Sebastian Vettel")
                .withLogin("Vettel")
                .withPassword("sebastian12345");

        Taxi taxi1 = taxiBuilder.build();
        long newTaxiID1 = taxiDAO.update(taxi1);

        Taxi taxi2 = TaxiBuilder.aTaxi().build();  //USE "DEFAULT USER"
        long newTaxiID2 = taxiDAO.update(taxi2);

    }

    @Test
    public void testUpdateTaxiRecord() throws SQLException {

        TaxiDAO taxiDAO = aTaxiDAO();

        TaxiBuilder taxiBuilder = TaxiBuilder.aTaxi()
                .withId((long) 0)
                .withName("Lewis Carl Davidson Hamilton")
                .withCar("BMW 530 SL")
                .withPhone("(+371) 20100100")
                .withLogin("hamilton")
                .withPassword("lewis123");

        Taxi taxi = taxiBuilder.build();
        taxi.setTaxiId(taxiDAO.update(taxi));
        taxi.setName("Jenson Alexander Lyons Button");
        taxiDAO.update(taxi);

    }

    @Test
    public void testGetTaxiByID() throws SQLException {

        TaxiDAO taxiDAO = aTaxiDAO();

        TaxiBuilder taxiBuilder = TaxiBuilder.aTaxi()
                .withId((long) 0)
                .withName("Kimi-Matias Räikkönen")
                .withPhone("(+370) 777 5555")
                .withLogin("kimi")
                .withPassword("matki");

        Taxi taxi = taxiBuilder.build();
        taxi.setTaxiId(taxiDAO.update(taxi));

        Taxi taxiGetById = taxiDAO.getById(taxi.getTaxiId());
        assertEquals(taxi.getTaxiId(), taxiGetById.getTaxiId());
    }

    @Test
    public void testDeleteTaxiByID() throws SQLException {

        TaxiDAO taxiDAO = aTaxiDAO();

        Taxi taxi = TaxiBuilder.aTaxi().build();
        taxi.setTaxiId(taxiDAO.update(taxi));

        int countOfTaxisBeforeDeleteOperation = taxiDAO.getAll().size();

        taxiDAO.delete(taxi);

        int countOfTaxisAfterDeleteOperation = taxiDAO.getAll().size();

        assertTrue(countOfTaxisBeforeDeleteOperation-1 == countOfTaxisAfterDeleteOperation);

    }

    @Test
    public void testGetListOfAllTaxis() throws SQLException {

        TaxiDAO taxiDAO = aTaxiDAO();

        Taxi taxi = TaxiBuilder.aTaxi().build();
        taxiDAO.update(taxi);

        List<Taxi> listOfTaxis = taxiDAO.getAll();
        assertTrue(listOfTaxis.size()>0);

    }


}