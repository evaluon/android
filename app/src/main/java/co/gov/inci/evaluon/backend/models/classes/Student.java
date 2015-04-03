package co.gov.inci.evaluon.backend.models.classes;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import co.gov.inci.evaluon.backend.models.interfaces.User;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
public class Student implements User {

    private String id, firstName, middleName, lastName, email, password;
    private Date birthday;

    @JsonProperty("evaluee") private Evaluee evaluee;

    public Student(){
        this(null, null, null, null, null, null, null, null);
    }

    public Student(String id, String firstName, String middleName, String lastName, String email,
                   String password, Date birthday, Evaluee evaluee) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.evaluee = evaluee;
    }

    @Override public String getId() {
        return id;
    }

    @Override public String getFirstName() {
        return firstName;
    }

    @Override public String getMiddleName() {
        return middleName;
    }

    @Override public String getLastName() {
        return lastName;
    }

    @Override public String getEmail() {
        return email;
    }

    @Override public String getPassword() {
        return password;
    }

    @Override public Date getBirthday() {
        return birthday;
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

    public Evaluee getEvaluee() {
        return evaluee;
    }

    public void setEvaluee(Evaluee evaluee) {
        this.evaluee = evaluee;
    }

}