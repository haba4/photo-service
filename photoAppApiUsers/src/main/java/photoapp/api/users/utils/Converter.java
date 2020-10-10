package photoapp.api.users.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter<V> {

    public String json(V v) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(v);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
