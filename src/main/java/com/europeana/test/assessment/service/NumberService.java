package com.europeana.test.assessment.service;

import com.europeana.test.assessment.model.ProblemModel;

public interface NumberService {
    public ProblemModel postUpperLimit(int maxLimit);
    public ProblemModel retrieveResult();
}
