package co.gov.inci.evaluon.backend.models.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import co.gov.inci.evaluon.backend.models.converters.JavascriptDateDeserializer;

/**
 * @author Pablo Dorado <pandres95@boolinc.co>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "RoleId")
@JsonSubTypes({

})
@JsonIgnoreProperties(ignoreUnknown = true)
public interface User {

    @JsonProperty("id") int getId();

    @JsonProperty("firstName")
    String getFirstName();
    @JsonProperty("middleName")
    String getMiddleName();
    @JsonProperty("lastName")
    String getLastName();
    @JsonProperty("fullName")
    String getFullName();

    @JsonProperty("email")
    String getEmail();
    @JsonProperty("password")
    String getPassword();

    @JsonProperty("phone")
    String getPhone();
    @JsonProperty("mobile")
    String getMobile();
    @JsonProperty("imageUrl")
    String getImageUrl() throws UnsupportedEncodingException;

    @JsonDeserialize(using = JavascriptDateDeserializer.class )
    @JsonProperty("birthday")
    Date getBirthday();

}
