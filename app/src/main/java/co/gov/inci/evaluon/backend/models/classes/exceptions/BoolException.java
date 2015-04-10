package co.gov.inci.evaluon.backend.models.classes.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;

import co.gov.inci.evaluon.R;

/*
 * @author Pablo Dorado <pandres95@boolinc.co>
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class BoolException extends Exception
        implements Serializable {

    public static final HashMap<String, Integer> ERROR_DICT = new HashMap<String, Integer>(){{
        put("missing_fields", R.string.error_missing_fields);
        put("no_active_groups", R.string.error_no_active_groups);
        put("no_active_period", R.string.error_no_active_period);
        put("no_active_test", R.string.error_no_active_tests);
        put("invalid_hotp_code", R.string.error_invalid_hotp_code);
    }};

    String code;

    public BoolException(){
        this("", "");
    }

    public BoolException(@JsonProperty("message") String message,
                         @JsonProperty("code") String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
