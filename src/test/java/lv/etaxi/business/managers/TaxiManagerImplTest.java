package lv.etaxi.business.managers;

import lv.etaxi.entity.Taxi;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by Aleks on 19.04.2016.
 */
public class TaxiManagerImplTest {

    @Autowired
    TaxiManagerImpl taxiManagerImpl;

    public TaxiManagerImplTest() {

        this.taxiManagerImpl = new TaxiManagerImpl();

    }

    @Test
    public void createNewTaxi() throws Exception {
        Taxi newTaxi = new Taxi((long)0, "name", "car", "phone", "login", "password");
        taxiManagerImpl.createNewTaxi(newTaxi);
    }

}