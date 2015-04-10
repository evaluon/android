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
        put("is_evaluee", R.string.error_is_evaluee);
        put("invalid_permissions", R.string.error_invalid_permissions);
        put("not_an_evaluee", R.string.error_not_an_evaluee);
        put("not_evaluees", R.string.error_not_evaluees);
        put("test_unopened", R.string.error_test_unopened);
        put("unabled_institution", R.string.error_unabled_institution);
        put("insuficient_privileges", R.string.error_insuficient_privileges);
        put("invalid_grant", R.string.error_invalid_grant);
        put("access_denied", R.string.error_access_denied);
        put("already_opened_test", R.string.error_already_opened_test);
        put("existing_user", R.string.error_existing_user);
        put("test_unavailable", R.string.error_test_unavailable);
        put("client_not_found", R.string.error_client_not_found);
        put("no_questions_available", R.string.error_no_questions_available);








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
