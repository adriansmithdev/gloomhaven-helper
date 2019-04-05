package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class RoomResponseBody {
    @NonNull
    private String hash;
    @NonNull
    private Integer scenarioNumber;
    @NonNull
    private Integer scenarioLevel;
    @NonNull
    private Integer round;
    @NonNull
    private List<ElementResponseBody> elements;

    public static RoomResponseBody create(Room room, List<ElementResponseBody> elements) {
        return new RoomResponseBody(
                room.getHash(),
                room.getScenarioNumber(),
                room.getScenarioLevel(),
                room.getRound(),
                elements
        );
    }
}
