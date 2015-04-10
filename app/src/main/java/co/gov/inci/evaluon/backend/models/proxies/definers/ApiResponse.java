package co.gov.inci.evaluon.backend.models.proxies.definers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.gov.inci.evaluon.backend.models.classes.exceptions.BoolException;

/*
 * @author Pablo Dorado <pandres95@boolinc.co>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse<T> {

    @JsonProperty("success") private boolean success;
    @JsonProperty("data") private T data;
    @JsonProperty("error") private BoolException error;

    public ApiResponse() {
        this(true, null);
    }

    public ApiResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess(){
        return success;
    }

    public T getData() {
        return data;
    }

    public BoolException getError() {
        return error;
    }
}