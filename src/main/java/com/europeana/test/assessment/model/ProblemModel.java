package com.europeana.test.assessment.model;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * POJO to save the maxLimit and corresponding result.
 */
@XmlRootElement
public class ProblemModel {

    private int maxLimit;
    private int result;
    private long timetaken; //in microseconds

    public ProblemModel() {
    }

    public ProblemModel(int maxLimit, int result, long timetaken) {
        this.maxLimit = maxLimit;
        this.result = result;
        this.timetaken = timetaken;
    }

    public int getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public long getTimetaken() {
        return timetaken;
    }

    public void setTimetaken(long timetaken) {
        this.timetaken = timetaken;
    }
}
