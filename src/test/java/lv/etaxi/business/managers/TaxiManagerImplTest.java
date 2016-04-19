package lv.etaxi.business.managers;

import lv.etaxi.entity.Taxi;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by Aleks on 19.04.2016.
 */
public class TaxiManagerImplTest {

    String name     = "name123";
    String phone    = "phone123";
    String car      = "car123";
    String login    = "login123";
    String password = "password123";

    Taxi newTaxi;

    TaxiManagerImpl taxiManagerImpl;

    public TaxiManagerImplTest() {
        newTaxi = new Taxi((long)0, name, car, phone, login, password);
        this.taxiManagerImpl = new TaxiManagerImpl();
    }

    public void createNewTaxi() throws Exception {
        taxiManagerImpl.createNewTaxi(newTaxi);
    }

    public void deleteTaxi() throws Exception {
        taxiManagerImpl.deleteTaxi(newTaxi);
    }

    @Test
    public void findTaxiById() throws Exception {
        //createNewCustomer();
        Taxi taxi = new Taxi((long)0, name, car, phone, login, password);
        taxiManagerImpl.createNewTaxi(taxi);

        Taxi taxiFind = taxiManagerImpl.findTaxiById(taxi.getTaxiId());

        assertNotNull(taxiFind);
        assertEquals(name,     taxiFind .getName());
        assertEquals(phone,    taxiFind .getPhone());
        assertEquals(password, taxiFind .getPassword());

        taxiManagerImpl.deleteTaxi(taxiFind);
        //deleteTaxi();
    }

    @Test
    public void findCustomerByLogin() throws Exception {
        createNewTaxi();

        Taxi taxiFind = taxiManagerImpl.findTaxiByLogin(login);

        assertNotNull(taxiFind);
        assertEquals(taxiFind.getName(),     name);
        assertEquals(taxiFind.getPhone(),    phone);
        assertEquals(taxiFind.getPassword(), password);

        deleteTaxi();
    }

    @Test
    public void updateTaxi() throws Exception {
        Taxi taxi = new Taxi((long)0, name, car, phone, login, password);
        taxiManagerImpl.createNewTaxi(taxi);

        Taxi taxiFind = taxiManagerImpl.findTaxiById(taxi.getTaxiId());
        taxiFind.setName("name555");

        taxiManagerImpl.updateTaxi(taxiFind);

        assertNotNull(taxiFind);
        assertEquals(taxiFind.getName(),     "name555");
        assertEquals(taxiFind.getPhone(),    phone);
        assertEquals(taxiFind.getPassword(), password);

        taxiManagerImpl.deleteTaxi(taxiFind);
    }


}