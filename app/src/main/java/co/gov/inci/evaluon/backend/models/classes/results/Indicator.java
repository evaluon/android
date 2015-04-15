package co.gov.inci.evaluon.backend.models.classes.results;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;

import co.gov.inci.evaluon.R;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Indicator implements Serializable {

    public static final HashMap<String, Integer> imagesMap = new HashMap<String, Integer>(){{
        put("bronze", R.drawable.ic_bronze);
        put("diamond", R.drawable.ic_diamond);
        put("gold", R.drawable.ic_gold);
        put("master", R.drawable.ic_master);
        put("platinum", R.drawable.ic_platinum);
        put("silver", R.drawable.ic_silver);
    }};

    public static final HashMap<String, String> namesMap = new HashMap<String, String>(){{
        put("bronze", "Bronce");
        put("diamond", "Diamante");
        put("gold", "Oro");
        put("master", "Maestro");
        put("platinum", "Platino");
        put("silver", "Plata");
    }};

    private double average, fullLevel;
    private int questions, remainingQuestions, level;
    private String levelName, description;

    public Indicator(){
        this(0, 0, 0, 0, 0, null, null);
    }

    public Indicator(double average, double fullLevel, int questions,
                     int remainingQuestions, int level, String levelName, String description) {
        this.average = average;
        this.fullLevel = fullLevel;
        this.questions = questions;
        this.remainingQuestions = remainingQuestions;
        this.level = level;
        this.levelName = levelName;
        this.description = description;
    }

    public double getAverage() {
        return average;
    }

    public double getFullLevel() {
        return fullLevel;
    }

    public int getQuestions() {
        return questions;
    }

    public int getRemainingQuestions() {
        return remainingQuestions;
    }

    public int getLevel() {
        return level;
    }

    public String getLevelName() {
        return levelName;
    }

    public String getDescription() {
        return description;
    }
}
