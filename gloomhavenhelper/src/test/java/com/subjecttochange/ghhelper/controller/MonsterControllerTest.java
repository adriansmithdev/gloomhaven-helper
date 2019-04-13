package com.subjecttochange.ghhelper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subjecttochange.ghhelper.Application;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.service.MonsterService;
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

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@ActiveProfiles("test")
public class MonsterControllerTest {

    private static final int BASE_STAT = 3;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private MonsterService monsterService;
    Monster monster;

    @Before
    public void setUp() throws Exception {
        monster = Monster.create("Bear", 15);
        ArrayList<String> attributes = new ArrayList<>();
        attributes.add("Fast running");
        attributes.add("Big jumps");
        monster.setAttributes(attributes);
        monster.setEliteAttributes(attributes);
        monster.setLevel(BASE_STAT);
        monster.setMovement(BASE_STAT);
        monster.setAttack(BASE_STAT);
        monster.setRange(BASE_STAT);
        monster.setEliteHealth(BASE_STAT * 2);
        monster.setEliteMove(BASE_STAT * 2);
        monster.setEliteAttack(BASE_STAT * 2);
        monster.setEliteRange(BASE_STAT * 2);
        monster = monsterService.createMonster(monster);
    }

    @After
    public void tearDown() throws Exception {
        monsterService.deleteMonster(monster.getId());
    }

    @Test
    public void getMonsters() throws Exception {
        mvc.perform(
                get("/monsters")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id", is(monster.getId().intValue())))
                .andExpect(jsonPath("$.content[0].name", is("Bear")))
                .andExpect(jsonPath("$.content[0].attributes", containsInAnyOrder(
                        is("Fast running"), is("Big jumps"))))
                .andExpect(jsonPath("$.content[0].eliteAttributes", containsInAnyOrder(
                        is("Fast running"), is("Big jumps"))))
                .andExpect(jsonPath("$.content[0].level", is(monster.getLevel())))
                .andExpect(jsonPath("$.content[0].health", is(monster.getHealth())))
                .andExpect(jsonPath("$.content[0].movement", is(monster.getMovement())))
                .andExpect(jsonPath("$.content[0].attack", is(monster.getAttack())))
                .andExpect(jsonPath("$.content[0].range", is(monster.getRange())))
                .andExpect(jsonPath("$.content[0].eliteHealth", is(monster.getEliteHealth())))
                .andExpect(jsonPath("$.content[0].eliteMove", is(monster.getEliteMove())))
                .andExpect(jsonPath("$.content[0].eliteAttack", is(monster.getEliteAttack())))
                .andExpect(jsonPath("$.content[0].eliteRange", is(monster.getEliteRange())));
    }

    @Test
    public void createMonster() throws Exception {
        String jsonBody = new ObjectMapper().writeValueAsString(monster);

        MvcResult result = mvc.perform(
                post("/monsters")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is("Bear")))
                .andExpect(jsonPath("$.attributes", containsInAnyOrder(
                        is("Fast running"), is("Big jumps"))))
                .andExpect(jsonPath("$.eliteAttributes", containsInAnyOrder(
                        is("Fast running"), is("Big jumps"))))
                .andExpect(jsonPath("$.level", is(monster.getLevel())))
                .andExpect(jsonPath("$.health", is(monster.getHealth())))
                .andExpect(jsonPath("$.movement", is(monster.getMovement())))
                .andExpect(jsonPath("$.attack", is(monster.getAttack())))
                .andExpect(jsonPath("$.range", is(monster.getRange())))
                .andExpect(jsonPath("$.eliteHealth", is(monster.getEliteHealth())))
                .andExpect(jsonPath("$.eliteMove", is(monster.getEliteMove())))
                .andExpect(jsonPath("$.eliteAttack", is(monster.getEliteAttack())))
                .andExpect(jsonPath("$.eliteRange", is(monster.getEliteRange())))
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        Monster createdMonster = new ObjectMapper().readValue(resultString, Monster.class);
        monsterService.deleteMonster(createdMonster.getId());
    }

    @Test
    public void updateMonster() throws Exception {
    }

    @Test
    public void deleteMonster() throws Exception {
        mvc.perform(
                delete("/monsters")
                        .param("id", monster.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        monster = monsterService.createMonster(monster);
    }
}