package co.gov.inci.evaluon.backend.models.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Answer implements Serializable {

    @JsonProperty("test_id") private Integer testId;
    @JsonProperty("question_id") private Integer questionId;
    @JsonProperty("answer_id") private Integer answerId;

    public Answer(){
        this(null, null, null);
    }

    public Answer(Integer testId, Integer questionId){
        this(testId, questionId, null);
    }

    public Answer(Integer testId, Integer questionId, Integer answerId) {
        this.testId = testId;
        this.questionId = questionId;
        this.answerId = answerId;
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
}
