package com.subjecttochange.ghhelper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subjecttochange.ghhelper.Application;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.service.MonsterInstanceService;
import com.subjecttochange.ghhelper.persistence.service.MonsterService;
import com.subjecttochange.ghhelper.persistence.service.RoomService;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@ActiveProfiles("test")
public class MonsterInstanceControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private RoomService roomService;
    @Autowired
    private MonsterService monsterService;
    @Autowired
    private MonsterInstanceService monsterInstanceService;
    private Room room;
    private Monster monster;
    private MonsterInstance monsterInstance;

    @Before
    public void setUp() throws Exception {
        room = roomService.createRoom(Room.create());

        monster = Monster.create("Joe Monster", 5);
        monster = monsterService.createMonster(monster);

        monsterInstance = MonsterInstance.create(5, false, room, monster);
        monsterInstance = monsterInstanceService.createMonster(room.getHash(), monsterInstance);
    }

    @After
    public void tearDown() throws Exception {
        monsterInstanceService.deleteMonster(room.getHash(), monsterInstance.getId());
        roomService.deleteRoom(room.getHash());
        monsterService.deleteMonster(monster.getId());
    }

    @Test
    public void getMonsterInstances() throws Exception {
        mvc.perform(
                get("/monsterinstances")
                        .param("hash", room.getHash())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id", notNullValue()))
                .andExpect(jsonPath("$.content[0].currentHealth", is(monsterInstance.getCurrentHealth())))
                .andExpect(jsonPath("$.content[0].isElite", is(monsterInstance.getIsElite())))
                .andExpect(jsonPath("$.content[0].activeStatuses", containsInAnyOrder()))
                .andExpect(jsonPath("$.content[0].token", is(monsterInstance.getToken())));
    }

    @Test
    public void createMonsterInstance() throws Exception {
        MonsterInstance request = MonsterInstance.create(10, true, room, monster);
        String jsonBody = new ObjectMapper().writeValueAsString(request);

        MvcResult result = mvc.perform(
                post("/monsterinstances")
                        .param("hash", room.getHash())
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.currentHealth", is(request.getCurrentHealth())))
                .andExpect(jsonPath("$.isElite", is(request.getIsElite())))
                .andExpect(jsonPath("$.activeStatuses", containsInAnyOrder()))
                .andExpect(jsonPath("$.monsterId", is(request.getMonsterId().intValue())))
                .andExpect(jsonPath("$.token", notNullValue()))
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        MonsterInstance createdMonster = new ObjectMapper().readValue(resultString, MonsterInstance.class);
        monsterInstanceService.deleteMonster(room.getHash(), createdMonster.getId());
    }

    @Test
    public void updateMonsterInstance() throws Exception {
        MonsterInstance request = MonsterInstance.create(15, false, room, monster);
        Monster monsterTempId = monsterService.createMonster(Monster.create("Small monster", 5));
        int oldToken = monsterInstance.getToken();
        request.setMonsterId(monsterTempId.getId());
        request.setToken(10);

        String jsonBody = new ObjectMapper().writeValueAsString(request);

        mvc.perform(
                put("/monsterinstances")
                        .param("hash", room.getHash())
                        .param("id", monsterInstance.getId().toString())
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.currentHealth", is(request.getCurrentHealth())))
                .andExpect(jsonPath("$.isElite", is(request.getIsElite())))
                .andExpect(jsonPath("$.activeStatuses", containsInAnyOrder()))
                .andExpect(jsonPath("$.monsterId", is(request.getMonsterId().intValue())))
                .andExpect(jsonPath("$.token", is(request.getToken())));

        monsterInstance.setMonsterId(monster.getId());
        monsterInstance.setToken(oldToken);
        monsterInstance.setMonster(monster);
        monsterInstanceService.updateMonster(room.getHash(), monsterInstance.getId(), monsterInstance);
        monsterService.deleteMonster(monsterTempId.getId());
    }

    @Test
    public void deleteMonsterInstance() throws Exception {
        mvc.perform(
                delete("/monsterinstances")
                        .param("hash", room.getHash())
                        .param("id", monsterInstance.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        monsterInstance = monsterInstanceService.createMonster(room.getHash(), monsterInstance);
    }
}