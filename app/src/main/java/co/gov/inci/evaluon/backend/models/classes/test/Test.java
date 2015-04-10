package co.gov.inci.evaluon.backend.models.classes.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

import co.gov.inci.evaluon.backend.models.converters.JavascriptDateDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Test {

    @JsonProperty("hotp") private int hotp;
    @JsonProperty("id") private int id;
    @JsonProperty("create_date")
    @JsonDeserialize(using = JavascriptDateDeserializer.class) private Date createDate;
    @JsonProperty("start_date")
    @JsonDeserialize(using = JavascriptDateDeserializer.class) private Date startDate;
    @JsonProperty("stop_date")
    @JsonDeserialize(using = JavascriptDateDeserializer.class) private Date stopDate;
    @JsonProperty("description") private String description;

    public Test() {
        this(-1, null, null, null, null);
    }

    public Test(int hotp){
        this();
        this.hotp = hotp;
    }

    public Test(int id, Date createDate, Date startDate, Date stopDate, String description) {
        this.id = id;
        this.createDate = createDate;
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getHotp() {
        return hotp;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public String getDescription() {
        return description;
    }
}
