package co.gov.inci.evaluon.backend.models.classes.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.interfaces.User;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
public class Evaluee extends User {

    public static int[] genders = {
            0,
            R.id.option_gender_male,
            R.id.option_gender_female
    };

    public static int[] disabilities = {
            R.id.option_disability_none,
            R.id.option_disability_blind,
            R.id.option_disability_low_vision,
            R.id.option_disability_blind_deaf
    };

    public static int[] types = {
            0,
            R.id.option_type_student,
            R.id.option_type_employee,
            R.id.option_type_college_applicant,
            R.id.option_type_job_applicant,
            R.id.option_type_other
    };

    public static int[] levels = {
            0,
            R.id.option_level_elementary,
            R.id.option_level_middle,
            R.id.option_level_govt_employee,
            R.id.option_level_college,
            R.id.option_level_other
    };

    private static int lookFor(int[] array, int id){
        for(int i = 0; i < array.length; i++)
            if(array[i] == id) return i;
        return -1;
    }

    public Evaluee(){
        this(null, null, null, null, null, null, null, null);
    }

    public Evaluee(String id, String firstName, String middleName, String lastName, String email,
                   String password, Date birthday, Info evaluee) {
        super(id, firstName, middleName, lastName, email, password, birthday);
        this.evaluee = evaluee;
    }

    public static int genderById(int id){
        return lookFor(genders, id);
    }

    public static int disabilitiesById(int id){
        return lookFor(disabilities, id);
    }

    public static int typesById(int id){
        return lookFor(types, id);
    }

    public static int levelsById(int id){
        return lookFor(levels, id);
    }

    @JsonProperty("evaluee") private Info evaluee;

    public Info getEvaluee() {
        return evaluee;
    }

    public void setEvaluee(Info evaluee) {
        this.evaluee = evaluee;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true) public static class Info implements Serializable {

        @JsonProperty("id") private String id;
        @JsonProperty("gender_id") private int gender = 0;
        @JsonProperty("disability_id") private int disability = 0;
        @JsonProperty("evaluee_type") private int type = 0;
        @JsonProperty("level_id") private int level = 0;

        public Info(){
            this(0, 0, 0, 0);
        }

        public Info(int gender, int disability, int type, int level) {
            this(null, gender, disability, type, level);
        }

        public Info(String id, int gender, int disability, int type, int level) {
            this.id = id;
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


}