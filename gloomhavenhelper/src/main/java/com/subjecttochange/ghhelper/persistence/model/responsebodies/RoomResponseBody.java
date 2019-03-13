package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import lombok.Data;
import lombok.NonNull;

@Data
public class RoomResponseBody {
    @NonNull
    private String hash;
    @NonNull
    private Integer scenario;
    @NonNull
    private Integer round;

    public static RoomResponseBody create(Room room) {
        return new RoomResponseBody(
                room.getHash(),
                room.getScenario(),
                room.getRound()
        );
    }
}
