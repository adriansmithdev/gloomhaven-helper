package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import lombok.Data;
import lombok.NonNull;

@Data
public class SingleActionResponseBody {

    @NonNull
    Long monsterId;
    MonsterActionResponseBody monsterActionResponseBody;

    public static SingleActionResponseBody create(Long monsterId) {
        return new SingleActionResponseBody(monsterId);
    }
}
