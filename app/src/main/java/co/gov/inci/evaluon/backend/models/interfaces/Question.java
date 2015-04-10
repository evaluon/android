package co.gov.inci.evaluon.backend.models.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

import co.gov.inci.evaluon.backend.models.classes.general.Image;
import co.gov.inci.evaluon.backend.models.classes.questions.ClosedQuestion;
import co.gov.inci.evaluon.backend.models.classes.questions.OpenQuestion;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "open")
@JsonSubTypes({
        @JsonSubTypes.Type(name= "0", value = ClosedQuestion.class),
        @JsonSubTypes.Type(name= "1", value = OpenQuestion.class)

})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Question implements Serializable {

    @JsonProperty("id") private int id;
    @JsonProperty("description_text") private String descriptionText;
    @JsonProperty("image") private Image image;

    public Question(){
        this(-1, null);
    }

    public Question(int id, String descriptionText){
        this(id, descriptionText, null);
    }

    public Question(int id, String descriptionText, Image image) {
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
