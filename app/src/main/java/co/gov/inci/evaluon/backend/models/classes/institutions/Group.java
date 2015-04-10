package co.gov.inci.evaluon.backend.models.classes.institutions;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import co.gov.inci.evaluon.backend.models.interfaces.User;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Group implements Serializable {

    @JsonProperty("id") private int id;
    @JsonProperty("user") private User user;

    Group() {
        this(-1, null);
    }

    Group(int id, User user) {
        this.id = id;

        this.user = user;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
