package com.subjecttochange.ghhelper.persistence.model.jsonio.responsebodies;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class SessionResponseBody {
    @NonNull
    private RoomResponseBody room;
    @NonNull
    private List<MonsterResponseBody> monsters;

    public static SessionResponseBody create(RoomResponseBody room, List<MonsterResponseBody> monsters) {
        return new SessionResponseBody(
                room,
                monsters
        );
    }
}
