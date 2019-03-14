package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.orm.Element;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
public class RoomResponseBody {
    @NonNull
    private String hash;
    @NonNull
    private Integer scenario;
    @NonNull
    private Integer round;
    @NonNull
    private Set<Element> elements;

    public static RoomResponseBody create(Room room) {
        return new RoomResponseBody(
                room.getHash(),
                room.getScenario(),
                room.getRound(),
                room.getElements()
        );
    }
}
