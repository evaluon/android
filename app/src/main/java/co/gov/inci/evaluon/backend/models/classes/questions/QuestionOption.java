package co.gov.inci.evaluon.backend.models.classes.questions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import co.gov.inci.evaluon.backend.models.classes.general.Image;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionOption implements Serializable {

    @JsonProperty("id") private int id;
    @JsonProperty("description_text") private String descriptionText;
    @JsonProperty("image") private Image image;

    public QuestionOption(){
        this(-1, null, null);
    }

    public QuestionOption(int id, String descriptionText, Image image) {
        this.id = id;
        this.descriptionText = descriptionText;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public Image getImage() {
        return image;
    }

}