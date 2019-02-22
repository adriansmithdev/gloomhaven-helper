package com.subjecttochange.ghhelper.persistence.model.jsonio.requestbodies;

import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import lombok.Data;

@Data
public class RoomRequestBody {
    private String hash;
    private boolean isHashDirty;

    private int scenarioNumber;
    private boolean isScenarioNumberDirty;

    public Room updateRoom(Room room) {
        if (hasHash()) {
            room.setHash(getHash());
        }
        if (hasScenarioNumber()) {
            room.setScenarioNumber(getScenarioNumber());
        }
        return room;
    }

    public void setHash(String hash) {
        this.hash = hash;
        isHashDirty = true;
    }

    public void setScenarioNumber(int scenarioNumber) {
        this.scenarioNumber = scenarioNumber;
        isScenarioNumberDirty = true;
    }

    public boolean hasHash() {
        return isHashDirty;
    }

    public boolean hasScenarioNumber() {
        return isScenarioNumberDirty;
    }
}
