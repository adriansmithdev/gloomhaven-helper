package com.subjecttochange.ghhelper.persistence.model.orm;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public @Data class ElementsActive {
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
}
