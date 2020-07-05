package com.europeana.test.assessment.service;

import com.europeana.test.assessment.model.ModelStore;
import com.europeana.test.assessment.model.ProblemModel;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NumberServiceImplTest {
    @Autowired
    private NumberService numberService;
    @Mock
    private ModelStore modelStore;

    @Test
    @DisplayName("Empty summary should be returned")
    public void testEmptyResult() {
        ProblemModel model = new ProblemModel();
        doReturn(model).when(modelStore).getModel();
        ProblemModel model1 = numberService.retrieveResult();
        // then
        assertThat(model1, is(nullValue()));
    }
    @Test
    public void testSetUpperLimit() {
        ProblemModel model = numberService.postUpperLimit(10);
        assertEquals(10, model.getMaxLimit());
        assertEquals(model.getResult(), 0);
    }
}
