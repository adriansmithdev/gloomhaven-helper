package com.subjecttochange.ghhelper.persistence.model.jsonio.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.helpers.RepoHelper;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
