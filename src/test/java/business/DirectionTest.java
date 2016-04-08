package business;

import lv.etaxi.business.direction.Direction;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Genady Zalesky on 14.03.2016
 */
public class DirectionTest {

    @Test
    public void pointsAdded(){

        Direction direction = new Direction("Latvija, Rīga, Āzenes iela, 12", "Latvija, Rīga, Kronvalda bulvāris, 1");
        final Map<String, String> params = direction.putParameters();
        assertEquals("Latvija, Rīga, Āzenes iela, 12", params.get("origin"));
        assertEquals("Latvija, Rīga, Kronvalda bulvāris, 1", params.get("destination"));
    }

    @Test
    public void urlReceived(){

        Direction direction = new Direction("Latvija, Rīga, Āzenes iela, 12", "Latvija, Rīga, Kronvalda bulvāris, 1");
        final Map<String, String> params = direction.putParameters();
        String url = direction.generateURL(params);
        String urlFirstCharacters = url.substring(0, Math.min(url.length(), 90));
        assertEquals("http://anonymouse.org/cgi-bin/anon-www.cgi/http://maps.googleapis.com/maps/api/directions/", urlFirstCharacters);
    }

    @Test
    public void isDistance() throws IOException, JSONException {

        Direction direction = new Direction("Latvija, Rīga, Āzenes iela, 12", "Latvija, Rīga, Kronvalda bulvāris, 2");
        final Map<String, String> params = direction.putParameters();
        String url = direction.generateURL(params);
        JSONObject response = direction.getResponse(url);
        JSONObject location = direction.getLocation(response);
        String distance = direction.distance(location);
        assertEquals("2,6 км", distance);
    }
}
