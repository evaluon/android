package co.gov.inci.evaluon.backend.models.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import co.gov.inci.evaluon.backend.models.classes.Student;
import co.gov.inci.evaluon.backend.models.converters.JavascriptDateDeserializer;

/**
 * @author Pablo Dorado <pandres95@boolinc.co>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "role")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "1", value = Student.class)
})
@JsonIgnoreProperties(ignoreUnknown = true)
public interface User {

    @JsonProperty("id") String getId();

    @JsonProperty("first_name")
    String getFirstName();
    @JsonProperty("middle_name")
    String getMiddleName();
    @JsonProperty("last_name")
    String getLastName();

    @JsonProperty("mail")
    String getEmail();
    @JsonProperty("password")
    String getPassword();

    @JsonDeserialize(using = JavascriptDateDeserializer.class )
    @JsonProperty("birth_date")
    Date getBirthday();

}
