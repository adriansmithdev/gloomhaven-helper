package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.Room;
import com.subjecttochange.ghhelper.persistence.model.monster.Monster;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;

@Data
public class RoomResponseBody {
    @NonNull
    private String hash;

    public static RoomResponseBody create(Room room) {
        return new RoomResponseBody(
                room.getHash()
        );
    }
}
