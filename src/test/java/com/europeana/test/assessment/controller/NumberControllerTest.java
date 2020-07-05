package com.europeana.test.assessment.controller;

import com.europeana.test.assessment.model.ProblemModel;
import com.europeana.test.assessment.service.NumberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new NumberControllerTest()).build();
    }
    @Test
    @DisplayName("POST /setUpperLimit - Success")
    void testSetUpperlimit() throws Exception {

        ProblemModel model = new ProblemModel();
        model.setMaxLimit(maxLimit1);
        doReturn(model).when(numberService).postUpperLimit(maxLimit1);
        mockMvc.perform(post("/api/findNumber/setUpperLimit").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(model)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.maxLimit", equalTo(model.getMaxLimit())));
    }

    @Test
    @DisplayName("GET /getResult - Success")
    void testGetResult() throws Exception {
        ProblemModel model = new ProblemModel(10, 2520,0);

        doReturn(model).when(numberService).retrieveResult();

        mockMvc.perform(get("/api/findNumber/getResult"))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.result", equalTo(model.getResult())))
                .andExpect(jsonPath("$.maxLimit", equalTo(model.getMaxLimit())));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
