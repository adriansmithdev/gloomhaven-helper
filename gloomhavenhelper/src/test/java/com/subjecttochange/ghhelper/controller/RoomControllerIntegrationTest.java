package com.subjecttochange.ghhelper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.subjecttochange.ghhelper.Application;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class RoomControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RoomRepository roomRepository;
    Room room;

    @Before
    public void setup() {
        roomRepository.save(Room.createWithHash("AABBCC"));
    }

    @After
    public void teardown() {
        Room dirtyRoom = roomRepository.findByHash("AABBCC").orElseThrow(() -> new ResourceNotFoundException());
        roomRepository.delete(dirtyRoom);
    }

    @Test
    public void getRoom_jsonAllRooms() throws Exception {
        Room room = roomRepository.findByHash("AABBCC")
                .orElseThrow(() -> new ResourceNotFoundException());

        mvc.perform(
                get("/rooms")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[*].hash", hasItem("AABBCC")))
                .andExpect(jsonPath("$.content[*].scenario", hasItem(0)))
                .andExpect(jsonPath("$.content[*].round", hasItem(0)));
    }

    @Test
    public void getRoom_jsonRoom() throws Exception {
        Room room = roomRepository.findByHash("AABBCC")
                .orElseThrow(() -> new ResourceNotFoundException());

        mvc.perform(
                get("/rooms")
                        .param("hash", room.getHash())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].hash", is("AABBCC")))
                .andExpect(jsonPath("$.content[0].scenario", is(0)))
                .andExpect(jsonPath("$.content[0].round", is(0)));
    }

    @Test
    public void createRoom_json() throws Exception {
        mvc.perform(
                post("/rooms")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.hash", notNullValue()))
                .andExpect(jsonPath("$.scenario", is(0)))
                .andExpect(jsonPath("$.round", is(0)));
    }

    @Test
    public void updateRoom_jsonBody() throws Exception {
        Room room = roomRepository.findByHash("AABBCC")
                .orElseThrow(() -> new ResourceNotFoundException());
        Room request = Room.createRoom("CCBBAA", 5, 10);
        String jsonBody = new ObjectMapper().writeValueAsString(request);

        mvc.perform(
                put("/rooms")
                        .param("hash", room.getHash())
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.hash", is("CCBBAA")))
                .andExpect(jsonPath("$.scenario", is(5)))
                .andExpect(jsonPath("$.round", is(10)));

        // cleanup for teardown
        room.setHash("AABBCC");
        roomRepository.save(room);
    }

    @Test
    public void deleteRoom_status200() throws Exception {
        Room room = roomRepository.findByHash("AABBCC")
                .orElseThrow(() -> new ResourceNotFoundException());

        mvc.perform(
                delete("/rooms")
                        .param("hash", room.getHash())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        roomRepository.save(room);
    }
}