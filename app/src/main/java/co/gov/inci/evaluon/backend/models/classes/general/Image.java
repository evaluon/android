package co.gov.inci.evaluon.backend.models.classes.general;

import android.content.Context;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import co.gov.inci.evaluon.R;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Image implements Serializable {

    @JsonProperty("id") private String id;
    @JsonProperty("location") private String location;
    @JsonProperty("description") private String description;
    private boolean cdn;

    public Image() {
        this(null, null, null);
    }

    public Image(String id, String location, String description) {
        this(id, location, description, true);
    }

    public Image(String id, String location, String description, boolean cdn){
        this.id = id;
        this.location = location;
        this.description = description;
        this.cdn = cdn;
    }

    public String getId() {
        return id;
    }

    public String getLocation(Context ctx) {
        return cdn ? String.format("%s/%s", ctx.getString(R.string.cdnUrl), location) :
                location;
    }

    public String getDescription() {
        return description;
    }

}
