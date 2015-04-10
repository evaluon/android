package co.gov.inci.evaluon.backend.models.classes.institutions;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import co.gov.inci.evaluon.backend.models.classes.general.Image;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Institution implements Serializable {

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("image") private Image image;

    Institution() {
        this(-1, null, null);
    }

    Institution(int id, String name, Image image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

}
