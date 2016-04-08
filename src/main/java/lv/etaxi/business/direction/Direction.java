package lv.etaxi.business.direction;

import com.google.common.collect.Maps;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Genady Zalesky on 14.03.2016
 */
public class Direction extends ParametersEncoder {

    final String proxy;
    final String baseUrl;
    String origin = "";
    String destination = "";

    public Direction(String origin, String destination){
        proxy = "http://anonymouse.org/cgi-bin/anon-www.cgi/";
        baseUrl = proxy + "http://maps.googleapis.com/maps/api/directions/json";// путь к Geocoding API по HTTP
        this.origin = origin;
        this.destination = destination;
    }

    public Map<String, String> putParameters(){

        final Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");// указывает, исходит ли запрос на геокодирование от устройства с датчиком
        params.put("language", "ru");// язык данные на котором мы хочем получить
        params.put("mode", "driving");// способ перемещения, может быть driving, walking, bicycling
        params.put("origin", origin);// адрес или текстовое значение широты и
        // отправного пункта маршрута
        params.put("destination", destination);// адрес или текстовое значение широты и долготы
        // долготы конечного пункта маршрута
        return params;
    }

    public String generateURL(Map<String, String> params){

        final String url = baseUrl + '?' + encodeParams(params);// генерируем путь с параметрами
        //System.out.println(url); // Можем проверить что вернет этот путь в браузере
        return url;
    }

    public JSONObject getResponse(String url) throws IOException, JSONException {

        final JSONObject response = JsonReader.read(url);// делаем запрос к вебсервису и получаем от него ответ
        return response;
    }

    public JSONObject getLocation(JSONObject response) throws JSONException {

        JSONObject location = response.getJSONArray("routes").getJSONObject(0);
        location = location.getJSONArray("legs").getJSONObject(0);
        return location;
    }

    public String distance(JSONObject location) throws JSONException {

        final String distance = location.getJSONObject("distance").getString("text");
        //System.out.println(distance);
        return distance;
    }
}