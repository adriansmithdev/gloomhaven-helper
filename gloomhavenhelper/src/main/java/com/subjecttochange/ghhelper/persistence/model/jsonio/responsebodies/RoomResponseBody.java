package com.subjecttochange.ghhelper.persistence.model.jsonio.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import lombok.Data;
import lombok.NonNull;

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
