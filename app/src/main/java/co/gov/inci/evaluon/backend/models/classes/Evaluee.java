package co.gov.inci.evaluon.backend.models.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import co.gov.inci.evaluon.R;

/**
 * Created by pandres95 on 4/3/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class Evaluee implements Serializable {

    public static int[] genders = {
            R.id.option_gender_female,
            R.id.option_gender_male
    };

    public static int[] disabilities = {
            R.id.option_disability_none,
            R.id.option_disability_blind,
            R.id.option_disability_low_vision,
            R.id.option_disability_blind_deaf
    };

    @JsonProperty("gender_id") private int gender = 0;
    @JsonProperty("disability_id") private int disability = 0;
    @JsonProperty("evaluee_type") private int type = 0;
    @JsonProperty("level_id") private int level = 0;

    public Evaluee(){
        this(0, 0, 0, 0);
    }

    public Evaluee(int gender, int disability, int type, int level) {
        this.gender = gender;
        this.disability = disability;
        this.type = type;
        this.level = level;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getDisability() {
        return disability;
    }

    public void setDisability(int disability) {
        this.disability = disability;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
