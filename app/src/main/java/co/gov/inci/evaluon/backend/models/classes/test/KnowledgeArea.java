package co.gov.inci.evaluon.backend.models.classes.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.gov.inci.evaluon.backend.models.classes.general.Image;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KnowledgeArea {

    @JsonProperty("id") private String id;
    @JsonProperty("description") private String description;
    @JsonProperty("image") private Image image;

    public KnowledgeArea(){
        this(null, null, null);
    }

    public KnowledgeArea(String id, String description, Image image) {
        this.id = id;
        this.description = description;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Image getImage() {
        return image;
    }
}
