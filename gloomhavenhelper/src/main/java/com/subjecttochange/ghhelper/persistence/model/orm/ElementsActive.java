package com.subjecttochange.ghhelper.persistence.model.orm;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.subjecttochange.ghhelper.persistence.model.orm.Element.ElementType;

@Data
public class ElementsActive {
    private Map<ElementType, Integer> elements;

    public ElementsActive() {
        this.elements = new HashMap<ElementType, Integer>(){{
            put(ElementType.FIRE, 0);
            put(ElementType.ICE, 0);
            put(ElementType.AIR, 0);
            put(ElementType.EARTH, 0);
            put(ElementType.LIGHT, 0);
            put(ElementType.DARK, 0);
        }};
    }


    /**
     * Will decrease every element by 1 but won't go lower than 0
     */
    public void decrementAllElements() {
        for (Map.Entry<ElementType, Integer> element: elements.entrySet()) {
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
        elements.put(ElementType.valueOf(elementName.toUpperCase()), elementStrength);
    }
}
