package co.gov.inci.evaluon.backend.models.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

import co.gov.inci.evaluon.backend.models.classes.user.Evaluator;
import co.gov.inci.evaluon.backend.models.classes.user.Evaluee;
import co.gov.inci.evaluon.backend.models.converters.JavascriptDateDeserializer;

/**
 * @author Pablo Dorado <pandres95@boolinc.co>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "role")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "1", value = Evaluee.class),
        @JsonSubTypes.Type(name = "2", value = Evaluator.class)
})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class User {

    @JsonProperty("id") private String id;
    @JsonProperty("first_name") private String firstName;
    @JsonProperty("middle_name") private String middleName;
    @JsonProperty("last_name") private String lastName;
    @JsonProperty("mail") private String email;
    @JsonProperty("password") private String password;

    @JsonDeserialize(using = JavascriptDateDeserializer.class )
    @JsonProperty("birth_date") private Date birthday;

    @JsonDeserialize(using = JavascriptDateDeserializer.class )
    @JsonProperty("register_date") private Date registerDate;

    public User(){
        this(null, null, null, null, null, null, null);
    }

    public User(String id, String firstName, String middleName, String lastName, String email,
                String password, Date birthday) {
        this(id, firstName, middleName, lastName, email, password, birthday, null);
    }

    public User(String id, String firstName, String middleName, String lastName, String email,
                   String password, Date birthday, Date registerDate) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.registerDate = registerDate;
    }

    public String getId() {
        return id;
    }

    @JsonIgnore
    public String getName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
