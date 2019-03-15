package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.orm.Element;
import lombok.Data;
import lombok.NonNull;

@Data
public class ElementResponseBody {
    @NonNull
    private Long id;
    @NonNull
    private Element.ElementType type;
    @NonNull
    private Integer strength;

    public static ElementResponseBody create(Element element) {
        return new ElementResponseBody(
                element.getId(),
                element.getType(),
                element.getStrength()
        );
    }
}
