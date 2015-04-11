package co.gov.inci.evaluon.backend.models.classes.questions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Answer implements Serializable {

    @JsonProperty("test_id") private Integer testId;
    @JsonProperty("question_id") private Integer questionId;
    @JsonProperty("answer_id") private Integer answerId;
    @JsonProperty("text") private String text;

    public Answer(){
        this(null, null);
    }

    public Answer(Integer testId, Integer questionId){
        this.testId = testId;
        this.questionId = questionId;
        this.text = null;
        this.answerId = null;
    }

    public Answer(Integer testId, Integer questionId, String text) {
        this.testId = testId;
        this.questionId = questionId;
        this.answerId = null;
        this.text = text;
    }

    public Answer(Integer testId, Integer questionId, Integer answerId) {
        this.testId = testId;
        this.questionId = questionId;
        this.answerId = answerId;
        this.text = null;
    }

    public Integer getTestId() {
        return testId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public String getText() {
        return text;
    }
}
