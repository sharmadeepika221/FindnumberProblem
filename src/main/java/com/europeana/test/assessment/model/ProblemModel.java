package com.europeana.test.assessment.model;

import org.springframework.stereotype.Component;

/**
 * POJO to save the maxLimit and corresponding result.
 */
public class ProblemModel {

    private int maxlimit;
    private int result;

    public ProblemModel() {
    }

    public ProblemModel(int maxlimit, int result) {
        this.maxlimit = maxlimit;
        this.result = result;
    }

    public int getMaxlimit() {
        return maxlimit;
    }

    public void setMaxlimit(int maxlimit) {
        this.maxlimit = maxlimit;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
