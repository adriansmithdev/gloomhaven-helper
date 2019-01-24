package com.subjecttochange.ghhelper.persistence.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

enum Element {
    FIRE,
    ICE,
    AIR,
    EARTH,
    LIGHT,
    DARK
}

public @Data class Elements {
    private Map<Element, Integer> elements;

    public Elements() {
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
