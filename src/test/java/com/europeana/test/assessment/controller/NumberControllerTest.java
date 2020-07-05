package com.europeana.test.assessment.controller;

import com.europeana.test.assessment.model.ProblemModel;
import com.europeana.test.assessment.service.NumberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class NumberControllerTest {

    private static final int maxLimit1 = 20;

    @MockBean
    private NumberService numberService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /setUpperLimit - Success")
    void testSetUpperlimit() throws Exception {

        ProblemModel model = new ProblemModel();
        model.setMaxlimit(maxLimit1);
        mockMvc.perform(post("/setUpperLimit").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(model)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.maxlimit", equalTo(model.getMaxlimit())));
    }

    @Test
    @DisplayName("GET /getResult - Success")
    void testGetChargingSessions() throws Exception {
        ProblemModel model = new ProblemModel(10, 2520);

        doReturn(model).when(numberService).retrieveResult();

        mockMvc.perform(get("/getResult"))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.result", equalTo(model.getResult())))
                .andExpect(jsonPath("$.maxlimit", equalTo(model.getMaxlimit())));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
