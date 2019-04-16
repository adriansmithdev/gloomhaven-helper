package com.subjecttochange.ghhelper.persistence.model.orm;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
public class ElementTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int SIX = 6;
    private List<Element> elements = new ArrayList<>();
    private Room room = new Room();

    @Before
    public void setUp() {
        elements = Element.createElementsForRoom(ZERO, room);
        room.setElements(elements);
    }


    @Test
    public void createElementsForRoom() {
        List<Element> testElements = Element.createElementsForRoom(ZERO, room);
        assertEquals(
                "Size should be 6",
                SIX,
                testElements.size()
        );
    }


    @Test
    public void decrementElementsByQuantity() {
        elements.get(0).setStrength(TWO);
        Element.decrementElementsByQuantity(room, ONE);
        assertEquals(
                "Strength should be reduced from 2 to 1",
                ONE,
                firstElementStrength()
        );
    }


    @Test
    public void updateElement() {
        Element request = Element.createElement(Element.ElementType.FIRE, TWO, room);
        elements.get(0).updateElement(request);
        assertEquals(
                "Strength should be 2",
                TWO,
                firstElementStrength());
    }


    @Test
    public void getStrength() {
        assertEquals(
                "Strength should be 0",
                ZERO,
                firstElementStrength()
        );
    }

    private int firstElementStrength() {
        return elements.get(0).getStrength();
    }
}