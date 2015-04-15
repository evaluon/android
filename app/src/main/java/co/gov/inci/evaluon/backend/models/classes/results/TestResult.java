package co.gov.inci.evaluon.backend.models.classes.results;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestResult implements Serializable {

    private int id, rightQuestions, totalQuestions;
    private double note;
    private String description, feedback;

    public TestResult(){
        this(-1, 0, 0, 0, null, null);
    }

    public TestResult(int id, int rightQuestions, int totalQuestions, double note,
                      String description, String feedback) {
        this.id = id;
        this.rightQuestions = rightQuestions;
        this.totalQuestions = totalQuestions;
        this.note = note;
        this.description = description;
        this.feedback = feedback;
    }

    public int getId() {
        return id;
    }

    public int getRightQuestions() {
        return rightQuestions;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public double getNote() {
        return note;
    }

    public String getDescription() {
        return description;
    }

    public String getFeedback() {
        return feedback;
    }
}
