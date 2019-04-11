package com.subjecttochange.ghhelper.persistence.model.orm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@ActiveProfiles("test")
public class ElementTest {

    public static final int ZERO = 0;
    private List<Element> elements = new ArrayList<>();
    private Room room = new Room();

    @Before
    public void setUp() throws Exception {
        elements = Element.createElementsForRoom(0, room);
        room.setElements(elements);
    }


    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void decrementElementsByQuantity() {
        elements.get(0).setStrength(2);
        Element.decrementElementsByQuantity(room, 1);
        int strength = elements.get(0).getStrength();
        assertEquals(
                "Strength should be 1",
                1,
                strength
        );
    }


    @Test
    public void updateElement() {

    }


    @Test
    public void getStrength() {
        int strength = elements.get(0).getStrength();
        assertEquals(
                "Strength should be 0",
                ZERO,
                strength
        );
    }
}