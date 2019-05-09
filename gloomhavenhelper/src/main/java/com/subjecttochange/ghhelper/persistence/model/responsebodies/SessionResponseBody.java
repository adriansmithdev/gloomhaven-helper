package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.orm.Stat;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Status;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class SessionResponseBody {
    @NonNull
    private RoomResponseBody room;
    @NonNull
    private List<MonsterResponseBody> monsters;
    @NonNull
    private List<StatusResponseBody> statuses;
    @NonNull
    private List<StatResponseBody> stats;

    public static SessionResponseBody create(RoomResponseBody room,
                                             List<MonsterResponseBody> monsters,
                                             List<StatusResponseBody> statuses,
                                             List<StatResponseBody> stats) {
        return new SessionResponseBody(
                room,
                monsters,
                statuses,
                stats
        );
    }
}
