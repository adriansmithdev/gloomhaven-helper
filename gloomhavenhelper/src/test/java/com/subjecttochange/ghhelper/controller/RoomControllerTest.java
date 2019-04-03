package com.subjecttochange.ghhelper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subjecttochange.ghhelper.Application;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class RoomControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RoomRepository roomRepository;
    private Room room;

    @Before
    public void setUp() {
        room = roomRepository.save(Room.createWithHash("AABBCC"));
    }

    @After
    public void tearDown() {
        Room dirtyRoom = roomRepository.findByHash("AABBCC").orElseThrow(() -> new ResourceNotFoundException());
        roomRepository.delete(dirtyRoom);
    }

    @Test
    public void getRoomAll() throws Exception {
        mvc.perform(
                get("/rooms")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].hash", is("AABBCC")))
                .andExpect(jsonPath("$.content[0].scenario", is(0)))
                .andExpect(jsonPath("$.content[0].round", is(0)));
    }

    @Test
    public void getRoomSingle() throws Exception {
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
    public void createRoom() throws Exception {
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
    public void updateRoom() throws Exception {
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
        room = roomRepository.save(room);
    }

    @Test
    public void deleteRoomStatus200() throws Exception {
        mvc.perform(
                delete("/rooms")
                        .param("hash", room.getHash())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        roomRepository.save(room);
    }
}