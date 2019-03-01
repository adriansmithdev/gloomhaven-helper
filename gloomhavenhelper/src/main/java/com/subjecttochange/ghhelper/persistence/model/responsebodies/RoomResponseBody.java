package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import lombok.Data;
import lombok.NonNull;

@Data
public class RoomResponseBody {
    @NonNull
    private String hash;
    @NonNull
    private int scenarioNumber;

    public static RoomResponseBody create(Room room) {
        return new RoomResponseBody(
                room.getHash(),
                room.getScenarioNumber()
        );
    }
}
