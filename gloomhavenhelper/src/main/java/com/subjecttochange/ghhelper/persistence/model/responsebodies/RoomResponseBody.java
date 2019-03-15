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
    private Integer scenario;
    @NonNull
    private Integer round;
    @NonNull
    private List<ElementResponseBody> elements;

    public static RoomResponseBody create(Room room, List<ElementResponseBody> elements) {
        return new RoomResponseBody(
                room.getHash(),
                room.getScenario(),
                room.getRound(),
                elements
        );
    }
}
