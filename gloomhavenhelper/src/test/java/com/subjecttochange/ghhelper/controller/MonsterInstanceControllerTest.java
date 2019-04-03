package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.Application;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.repository.MonsterInstanceRepository;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class MonsterInstanceControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MonsterRepository monsterRepository;
    @Autowired
    private MonsterInstanceRepository monsterInstanceRepository;
    private Room room;
    private Monster monster;
    private MonsterInstance monsterInstance;

    @Before
    public void setUp() throws Exception {
        room = roomRepository.save(Room.createWithHash("AABBCC"));
        monster = monsterRepository.save(Monster.create("Joe Monster", 5));
        monsterInstance = monsterInstanceRepository.save(MonsterInstance.create(5, false, room, monster));
    }

    @After
    public void tearDown() throws Exception {
        monsterInstance = monsterInstanceRepository.findById(monsterInstance.getId()).orElseThrow(() -> new ResourceNotFoundException());
        room = roomRepository.findByHash("AABBCC").orElseThrow(() -> new ResourceNotFoundException());
        monster = monsterRepository.findById(monster.getId()).orElseThrow(() -> new ResourceNotFoundException());

        monsterInstanceRepository.delete(monsterInstance);
        roomRepository.delete(room);
        monsterRepository.delete(monster);
    }

    @Test
    public void getMonsterInstances() throws Exception {
        mvc.perform(
                get("/monsterinstances")
                        .param("hash", room.getHash())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id", is(room.getId().intValue())))
                .andExpect(jsonPath("$.content[0].currentHealth", is(5)))
                .andExpect(jsonPath("$.content[0].isElite", is(false)))
                .andExpect(jsonPath("$.content[0].activeStatuses", containsInAnyOrder()))
                .andExpect(jsonPath("$.content[0].token", nullValue()));
    }

    @Test
    public void createMonsterInstance() {
    }

    @Test
    public void updateMonsterInstance() {
    }

    @Test
    public void deleteMonsterInstance() {
    }
}