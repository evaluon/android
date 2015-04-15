package co.gov.inci.evaluon.backend.models.classes.questions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Answer implements Serializable {

    @JsonProperty("test_id") private String testId;
    @JsonProperty("question_id") private Integer questionId;
    @JsonProperty("answer_id") private String answerId;
    @JsonProperty("text") private String text;

    public Answer(){
        this(null, null);
    }

    public Answer(Integer testId, Integer questionId){
        this.testId = String.valueOf(testId);
        this.questionId = questionId;
        this.text = null;
        this.answerId = null;
    }

    public Answer(Integer testId, Integer questionId, String text) {
        this.testId = String.valueOf(testId);
        this.questionId = questionId;
        this.answerId = null;
        this.text = text;
    }

    public Answer(Integer testId, Integer questionId, Integer answerId) {
        this.testId = String.valueOf(testId);
        this.questionId = questionId;
        this.answerId = String.valueOf(answerId);
        this.text = null;
    }

    public String getTestId() {
        return testId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public String getText() {
        return text;
    }

}
