package co.gov.inci.evaluon.backend.models.proxies.definers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;

/**
 * @author Pablo Dorado <pandres95@boolinc.co>
 */
public abstract class JSONObjectProxy<T> {

    private ObjectMapper objectMapper;
    private T originalObject;

    public JSONObjectProxy(JSONObject jsonObject, Class<T> type) throws IOException {

        objectMapper = new ObjectMapper();
        originalObject = objectMapper.readValue(jsonObject.toString(), type);

    }

    protected T getOriginalObject() {
        return originalObject;
    }

}
