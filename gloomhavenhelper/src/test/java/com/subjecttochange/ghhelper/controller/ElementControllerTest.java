package com.subjecttochange.ghhelper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subjecttochange.ghhelper.Application;
import com.subjecttochange.ghhelper.persistence.model.orm.Element;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
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

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@ActiveProfiles("test")
public class ElementControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private RoomService roomService;
    private Room room;

    @Before
    public void setUp() throws Exception {
        room = roomService.createRoom(Room.createWithRandomHash());
    }

    @After
    public void tearDown() throws Exception {
        roomService.deleteRoom(room.getHash());
    }

    @Test
    public void getElement() throws Exception {
        mvc.perform(
                get("/elements")
                        .param("hash", room.getHash())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[*].type", containsInAnyOrder("FIRE", "ICE", "AIR", "EARTH", "LIGHT", "DARK")))
                .andExpect(jsonPath("$.content[*].strength", containsInAnyOrder(0, 0, 0, 0, 0, 0)));
    }

    @Test
    public void updateElement() throws Exception {
        Element request = Element.createElement(Element.ElementType.FIRE, 2, room);
        String jsonBody = new ObjectMapper().writeValueAsString(request);
        Element firstElement = room.getElements().get(0);

        mvc.perform(
                put("/elements")
                        .param("hash", room.getHash())
                        .param("id", firstElement.getId().toString())
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", is(firstElement.getType().toString())))
                .andExpect(jsonPath("$.strength", is(request.getStrength())));
    }
}