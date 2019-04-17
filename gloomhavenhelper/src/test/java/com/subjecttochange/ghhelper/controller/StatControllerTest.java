package com.subjecttochange.ghhelper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subjecttochange.ghhelper.Application;
import com.subjecttochange.ghhelper.persistence.model.orm.Element;
import com.subjecttochange.ghhelper.persistence.model.orm.Stat;
import com.subjecttochange.ghhelper.persistence.service.StatService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@ActiveProfiles("test")
public class StatControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private StatService statService;
    private Stat stat;

    @Before
    public void setUp() throws Exception {
        stat = stat.create("Strength", "Big strong arms");
        stat = statService.createStat(stat);
    }

    @After
    public void tearDown() throws Exception {
        statService.deleteStat(stat.getId());
    }

    @Test
    public void getStat() throws Exception {
        mvc.perform(
                get("/stats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id", notNullValue()))
                .andExpect(jsonPath("$.content[0].name", is(stat.getName())))
                .andExpect(jsonPath("$.content[0].tooltip", is(stat.getTooltip())));
    }

    @Test
    public void createStat() throws Exception {
        String jsonBody = new ObjectMapper().writeValueAsString(stat);

        MvcResult result = mvc.perform(
                post("/stats")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(stat.getName())))
                .andExpect(jsonPath("$.tooltip", is(stat.getTooltip())))
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        Stat createdStat = new ObjectMapper().readValue(resultString, Stat.class);
        statService.deleteStat(createdStat.getId());
    }

    @Test
    public void updateStat() throws Exception {
        Stat request = Stat.create("Weak", "Small arms");
        String jsonBody = new ObjectMapper().writeValueAsString(request);

        mvc.perform(
                put("/stats")
                        .param("id", stat.getId().toString())
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(request.getName())))
                .andExpect(jsonPath("$.tooltip", is(request.getTooltip())));
    }

    @Test
    public void deleteStat() throws Exception {
        mvc.perform(
                delete("/stats")
                        .param("id", stat.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        stat = statService.createStat(stat);
    }
}