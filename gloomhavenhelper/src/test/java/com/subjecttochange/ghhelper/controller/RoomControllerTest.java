package com.subjecttochange.ghhelper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subjecttochange.ghhelper.Application;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@ActiveProfiles("test")
public class RoomControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;
    private Room room;

    @Before
    public void setUp() {
        room = roomService.createRoom(Room.create());
    }

    @After
    public void tearDown() {
        roomService.deleteRoom(room.getHash());
    }

    @Test
    public void getRoomSingle() throws Exception {
        mvc.perform(
                get("/rooms")
                        .param("hash", room.getHash())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].hash", is(room.getHash())))
                .andExpect(jsonPath("$.content[0].scenarioNumber", is(room.getScenarioNumber())))
                .andExpect(jsonPath("$.content[0].round", is(room.getRound())));
    }

    @Test
    public void createRoom() throws Exception {
        String jsonBody = new ObjectMapper().writeValueAsString(roomService.createRoom(room));

        MvcResult result = mvc.perform(
                post("/rooms")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.hash", notNullValue()))
                .andExpect(jsonPath("$.scenarioNumber", is(room.getScenarioNumber())))
                .andExpect(jsonPath("$.scenarioLevel", is(room.getScenarioLevel())))
                .andExpect(jsonPath("$.round", is(room.getRound())))
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        Room roomResult = new ObjectMapper().readValue(resultString, Room.class);
        roomService.deleteRoom(roomResult.getHash());
    }

    @Test
    public void updateRoom() throws Exception {
        Room request = Room.create(5, 7);
        String jsonBody = new ObjectMapper().writeValueAsString(request);

        mvc.perform(
                put("/rooms")
                        .param("hash", room.getHash())
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.hash", notNullValue()))
                .andExpect(jsonPath("$.scenarioNumber", is(request.getScenarioNumber())))
                .andExpect(jsonPath("$.scenarioLevel", is(request.getScenarioLevel())))
                .andExpect(jsonPath("$.round", is(request.getRound())));

        roomService.updateRoom(room.getHash(), room);
    }

    @Test
    public void deleteRoomStatus200() throws Exception {
        mvc.perform(
                delete("/rooms")
                        .param("hash", room.getHash())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        room = roomService.createRoom(room);
    }
}