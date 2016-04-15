package business;

import lv.etaxi.business.security.Password;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Aleks on 03.03.2016.
 */
public class PasswordTest {

    @Test
         public void testGetSaltedHash() throws Exception {
        String pass = Password.getSaltedHash("abcda");
        assertTrue(Password.check("abcda", pass));
    }

    @Test
    public void testGetSaltedHash2() throws Exception {
        String pass = Password.getSaltedHash("abcda");
        assertTrue(!Password.check("aaaaa2344", pass));
    }

}