package com.europeana.test.assessment.service.impl;

import ch.qos.logback.classic.Logger;
import com.europeana.test.assessment.model.ProblemModel;
import com.europeana.test.assessment.model.ModelStore;
import com.europeana.test.assessment.service.NumberService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class to solve the given problem
 */
@Service
public class NumberServiceImpl implements NumberService {
    @Autowired
    private ModelStore modelStore;

    Logger log = (Logger) LoggerFactory.getLogger(NumberServiceImpl.class);

    /**
     * This methos will save the maxlimit value sent by
     * the user to object of ProblemModel.
     * @param maxlimit
     * @return ProblemModel
     */
    @Override
    public ProblemModel postUpperLimit(int maxlimit) {
        ProblemModel newModel = new ProblemModel();
        newModel.setMaxLimit(maxlimit);
        modelStore.setModel(newModel);
        return newModel;
    }

    /**
     * This method will calculate the result from the set maxLimit of model object
     * and save the result into model object.
     * @return ProblemModel
     */
    @Override
    public ProblemModel retrieveResult() {
        ProblemModel model =  modelStore.getModel();
        if(model != null && model.getMaxLimit() != 0){
            long startTime = System.nanoTime();
            int result = findMinNumberDivisibleByRange(model.getMaxLimit());
            long endTime = System.nanoTime();
            log.info("Maximum upper limit for the problem is {} and result is {}", model.getMaxLimit(), result);
            model.setResult(result);
            model.setTimetaken((endTime-startTime)/1000);
        }
        return model;
    }

    private int findMinNumberDivisibleByRange(int upperLimit) {
        int result =1;
        for(int i = 2; i <= upperLimit; i++) {
            result = (result * i) / getGCD( result, i);
        }
        return result;
    }

    /**
     * This method will return the GCD value.
     * @param result
     * @param i
     * @return int
     */
    private int getGCD(int result, int i) {
        int temp;
        while (result % i != 0) {
            temp = i;
            i = result % i;
            result = temp;
        }
        return i;
    }
}
