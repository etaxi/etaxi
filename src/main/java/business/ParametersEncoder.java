package business;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Genady Zalesky on 14.03.2016
 */
public class ParametersEncoder {

    protected static String encodeParams(final Map<String, String> params) {

        final String paramsUrl = Joiner.on('&').join(// получаем значение вида key1=value1&key2=value2...
                Iterables.transform(params.entrySet(), new Function<Map.Entry<String, String>, String>() {

                    @Override
                    public String apply(final Map.Entry<String, String> input) {

                        try {

                            final StringBuffer buffer = new StringBuffer();
                            buffer.append(input.getKey());// получаем значение вида key=value
                            buffer.append('=');
                            buffer.append(URLEncoder.encode(input.getValue(), "utf-8"));// кодирует строку в
                            // соответствии со стандартом
                            // HTML 4.01
                            return buffer.toString();
                        } catch (final UnsupportedEncodingException e) {

                            throw new RuntimeException(e);
                        }
                    }
                }));
        return paramsUrl;
    }
}
