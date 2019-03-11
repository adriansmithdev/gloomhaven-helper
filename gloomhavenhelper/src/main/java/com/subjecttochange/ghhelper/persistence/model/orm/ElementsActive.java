package com.subjecttochange.ghhelper.persistence.model.orm;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ElementsActive {
    private Map<Element, Integer> elements;

    public ElementsActive() {
        this.elements = new HashMap<Element, Integer>(){{
            put(Element.FIRE, 0);
            put(Element.ICE, 0);
            put(Element.AIR, 0);
            put(Element.EARTH, 0);
            put(Element.LIGHT, 0);
            put(Element.DARK, 0);
        }};
    }


    /**
     * Will decrease every element by 1 but won't go lower than 0
     */
    public void decrementAllElements() {
        for (Map.Entry<Element, Integer> element: elements.entrySet()) {
            element.setValue(Math.max(0, element.getValue() - 1));
        }
    }


    /**
     * Will move given element name to next value
     * 0 -> 2 -> 1 -> 0
     * @param elementName String value for element case-insensitive
     */
    public void stepElement(String elementName) {
        Integer elementStrength = elements.get(elementName);
        if (elementStrength == 0) {
            elementStrength += 2;
        } else {
            elementStrength--;
        }
        elements.put(Element.valueOf(elementName.toUpperCase()), elementStrength);
    }
}
