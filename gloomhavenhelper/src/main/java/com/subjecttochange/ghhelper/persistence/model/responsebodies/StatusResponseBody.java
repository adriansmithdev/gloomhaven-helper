package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.orm.Element;
import com.subjecttochange.ghhelper.persistence.model.orm.Stat;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Status;
import lombok.Data;
import lombok.NonNull;

@Data
public class StatusResponseBody {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String tooltip;

    public static StatusResponseBody create(Status status) {
        return new StatusResponseBody(
                status.getId(),
                status.getName(),
                status.getTooltip()
        );
    }
}
