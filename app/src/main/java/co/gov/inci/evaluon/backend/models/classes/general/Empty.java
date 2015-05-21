package co.gov.inci.evaluon.backend.models.classes.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Empty implements Serializable {
    public Empty() { }
}
