package com.subjecttochange.ghhelper.persistence.model.responsebodies;

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
    private List<Status> statuses;

    public static SessionResponseBody create(
            RoomResponseBody room,
            List<MonsterResponseBody> monsters,
            List<Status> statuses

    ) {
        return new SessionResponseBody(
                room,
                monsters,
                statuses
        );
    }
}
