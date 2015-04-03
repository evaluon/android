package co.gov.inci.evaluon.backend.models.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

import co.gov.inci.evaluon.backend.models.interfaces.User;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class UserPassword implements Serializable {

    @JsonProperty("password") private String password;

    public UserPassword(){
        this(null);
    }

    public UserPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}