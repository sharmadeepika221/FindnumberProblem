package com.europeana.test.assessment.controller;

import com.europeana.test.assessment.exception.ResourceNotFoundException;
import com.europeana.test.assessment.model.ProblemModel;
import com.europeana.test.assessment.service.NumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;


/**
 * Controller class
 */
@RestController
public class NumberController extends AbstractRestHandler{

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NumberService numberService;

    /**
     * This method is to set the upper limit.
     *
     * @return ResponseEntity<List<ProblemModel>>
     */
    @PostMapping("/setUpperLimit")
    @ResponseStatus(HttpStatus.OK)
    //@ApiOperation(value = "Sets the upper limit.", notes = "")
    public ResponseEntity<ProblemModel> setUpperLimit(
            @RequestBody @Valid ProblemModel model) {
        ProblemModel newModel = numberService.postUpperLimit(model.getMaxlimit());
        if (newModel == null) {
            log.error("Please pass a valid upper limit");
            throw new ResourceNotFoundException("Upper limit can not be set.");
        }
        return new ResponseEntity<ProblemModel>(model, HttpStatus.OK);
    }


    /**
     * This method is for retrieving the result of the given problem.
     *
     * @return ResponseEntityt<ProblemModel>
     */
    @RequestMapping(value = "/getResult", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
   // @ApiOperation(value = "Get the Result")
    public ResponseEntity<ProblemModel> getProblemResult() {
        ProblemModel model = numberService.retrieveResult();
        if (model == null) {
            log.error("Please make sure upper limit has been provided.");
            throw new ResourceNotFoundException("Result car not retreived");
        }
        return new ResponseEntity<ProblemModel>(model, HttpStatus.OK);
    }

}
