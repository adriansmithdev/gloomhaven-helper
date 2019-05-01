package com.subjecttochange.ghhelper.controller;

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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@ActiveProfiles("test")
public class SessionControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private RoomService roomService;
    @Autowired
    private MonsterInstanceService monsterInstanceService;
    @Autowired
    private MonsterService monsterService;
    private Room room;
    private MonsterInstance monsterInstance;
    private Monster monster;

    @Before
    public void setUp() throws Exception {
        room = roomService.createRoom(Room.create());
        monster = monsterService.createMonster(Monster.create("Baggon", 5));
        monsterInstance = monsterInstanceService.createMonster(
                room.getHash(),
                MonsterInstance.create(true, room, monster)
        );
    }

    @After
    public void tearDown() throws Exception {
        monsterInstanceService.deleteMonster(room.getHash(), monsterInstance.getId());
        monsterService.deleteMonster(monster.getId());
        roomService.deleteRoom(room.getHash());
    }

    @Test
    public void getRooms() throws Exception {
        mvc.perform(
                get("/sessions")
                        .param("hash", room.getHash())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].room.hash", is(room.getHash())))
                .andExpect(jsonPath("$.content[0].room.scenarioNumber", is(room.getScenarioNumber())))
                .andExpect(jsonPath("$.content[0].room.scenarioLevel", is(room.getScenarioLevel())))
                .andExpect(jsonPath("$.content[0].room.round", is(room.getRound())))
                .andExpect(jsonPath("$.content[0].room.elements", notNullValue()))
                .andExpect(jsonPath("$.content[0].monsters", notNullValue()))
                .andExpect(jsonPath("$.content[0].statuses", notNullValue()))
                .andExpect(jsonPath("$.content[0].stats", notNullValue()));
    }
}