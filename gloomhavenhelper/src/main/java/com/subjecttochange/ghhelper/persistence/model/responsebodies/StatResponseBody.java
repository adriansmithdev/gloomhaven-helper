package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.orm.Stat;
import lombok.Data;
import lombok.NonNull;

@Data
public class StatResponseBody {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String tooltip;

    public static StatResponseBody create(Stat stat) {
        return new StatResponseBody(
                stat.getId(),
                stat.getName(),
                stat.getTooltip()
        );
    }
}
