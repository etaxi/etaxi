package lv.etaxi.business.managers;

import lv.etaxi.business.TaxiManager;
import lv.etaxi.config.SpringAppConfig;
import lv.etaxi.entity.Taxi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Aleks on 19.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
public class TaxiManagerImplTest {

    String name     = "name123";
    String phone    = "phone123";
    String car      = "car123";
    String login    = "login123";
    String password = "password123";

    Taxi newTaxi;

    @Autowired
    TaxiManager taxiManagerImpl;

    public TaxiManagerImplTest() {
        newTaxi = new Taxi((long) 0, name, car, phone, login, password);
    }

    public void createNewTaxi() throws Exception {
        taxiManagerImpl.create(newTaxi);
    }

    public void deleteTaxi() throws Exception {
        taxiManagerImpl.delete(newTaxi);
    }

    @Test
    public void findTaxiById() throws Exception {

        Taxi taxi = new Taxi((long) 0, name, car, phone, login, password);
        taxiManagerImpl.create(taxi);

        Taxi taxiFind = taxiManagerImpl.findById(taxi.getTaxiId());

        assertNotNull(taxiFind);
        assertEquals(name,     taxiFind .getName());
        assertEquals(phone,    taxiFind .getPhone());
        assertEquals(password, taxiFind .getPassword());

        taxiManagerImpl.delete(taxiFind);

        Taxi taxiFindAfterDelete = taxiManagerImpl.findById(taxiFind.getTaxiId());
        assertEquals(taxiFindAfterDelete, null);
    }

    @Test
    public void findTaxiByLogin() throws Exception {

        createNewTaxi();

        Taxi taxiFind = taxiManagerImpl.findByLogin(login);

        assertNotNull(taxiFind);
        assertEquals(taxiFind.getName(),     name);
        assertEquals(taxiFind.getPhone(),    phone);
        assertEquals(taxiFind.getPassword(), password);

        deleteTaxi();

        Taxi taxiFindAfterDelete = taxiManagerImpl.findById(taxiFind.getTaxiId());
        assertEquals(taxiFindAfterDelete, null);
    }

    @Test
    public void updateTaxi() throws Exception {
        Taxi taxi = new Taxi((long) 0, name, car, phone, login, password);
        taxiManagerImpl.create(taxi);

        Taxi taxiFind = taxiManagerImpl.findById(taxi.getTaxiId());
        taxiFind.setName("name555");

        taxiManagerImpl.update(taxiFind);

        taxiManagerImpl.update(taxiFind);
        Taxi taxiFindAfterUpdate = taxiManagerImpl.findById(taxiFind.getTaxiId());

        assertNotNull(taxiFindAfterUpdate);
        assertEquals(taxiFindAfterUpdate.getName(),     "name555");
        assertEquals(taxiFindAfterUpdate.getPhone(),    phone);
        assertEquals(taxiFindAfterUpdate.getPassword(), password);

        taxiManagerImpl.delete(taxiFind);

        Taxi taxiFindAfterDelete = taxiManagerImpl.findById(taxiFind.getTaxiId());
        assertEquals(taxiFindAfterDelete, null);
    }


}