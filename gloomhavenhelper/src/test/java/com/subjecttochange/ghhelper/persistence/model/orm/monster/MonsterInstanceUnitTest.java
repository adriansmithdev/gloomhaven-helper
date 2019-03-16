package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MonsterInstanceUnitTest {

    private List<MonsterInstance> monsterInstances;
    private Room room;
    private Monster monster;

    @Before
    public void setUp() {
        monsterInstances = new ArrayList<>();
        room = Room.createWithHash("TEST");
        monster = Monster.create("Big Wolf", 10);
    }

    // [] --> expect 1
    @Test
    public void nextToken_EmptyList() {
        assertEquals(
                "Expected token to be equal to 1 when given no previous instances",
                1,
                MonsterInstance.nextAvailableToken(monsterInstances).intValue()
        );
    }

    // 1, 2, 3, 4, 5 --> expect 6
    @Test
    public void nextToken_InOrderLastElement() {
        List<Integer> startValues = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        monsterInstances.addAll(populateMonsterInstances(startValues));

        assertEquals(
                "Should return next available index",
                6,
                MonsterInstance.nextAvailableToken(monsterInstances).intValue()
        );
    }

    // 3, 4, 5 --> expect 1
    @Test
    public void nextToken_InOrderFirstElement() {
        List<Integer> startValues = new ArrayList<>(Arrays.asList(3, 4, 5));
        monsterInstances.addAll(populateMonsterInstances(startValues));

        assertEquals(
                "Should find first available number",
                1,
                MonsterInstance.nextAvailableToken(monsterInstances).intValue()
        );
    }

    // 1, 2, 4, 5 --> expect 3
    @Test
    public void nextToken_InOrderMiddleElement() {
        List<Integer> startValues = new ArrayList<>(Arrays.asList(1, 2, 4, 5));
        monsterInstances.addAll(populateMonsterInstances(startValues));

        assertEquals(
                "Should find first available number in sequence",
                3,
                MonsterInstance.nextAvailableToken(monsterInstances).intValue()
        );
    }

    // 1, 2, 3, 3, 3 --> expect 4
    @Test
    public void nextToken_InOrderRepeatedEnd() {
        List<Integer> startValues = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 3));
        monsterInstances.addAll(populateMonsterInstances(startValues));

        assertEquals(
                "Should find last available in repeated sequence",
                4,
                MonsterInstance.nextAvailableToken(monsterInstances).intValue()
        );
    }

    // 1, 1, 1, 2, 3 --> expect 4
    @Test
    public void nextToken_InOrderRepeatedStart() {
        List<Integer> startValues = new ArrayList<>(Arrays.asList(1, 1, 1, 2, 3));
        monsterInstances.addAll(populateMonsterInstances(startValues));

        assertEquals(
                "Should find first available in repeated sequence",
                4,
                MonsterInstance.nextAvailableToken(monsterInstances).intValue()
        );
    }

    // 1, 2, 2, 4 --> expect 3
    @Test
    public void nextToken_InOrderRepeatedGap() {
        List<Integer> startValues = new ArrayList<>(Arrays.asList(1, 2, 2, 4));
        monsterInstances.addAll(populateMonsterInstances(startValues));

        assertEquals(
                "Should find first available in repeated sequence",
                3,
                MonsterInstance.nextAvailableToken(monsterInstances).intValue()
        );
    }

    // 1 --> expect 2
    @Test
    public void nextToken_OneElementOrdered() {
        List<Integer> startValues = new ArrayList<>(Collections.singletonList(1));
        monsterInstances.addAll(populateMonsterInstances(startValues));

        assertEquals(
                "Should find last available in series",
                2,
                MonsterInstance.nextAvailableToken(monsterInstances).intValue()
        );
    }

    // 2 --> expect 1
    @Test
    public void nextToken_OneElement() {
        List<Integer> startValues = new ArrayList<>(Collections.singletonList(2));
        monsterInstances.addAll(populateMonsterInstances(startValues));

        assertEquals(
                "Should find first available in series",
                1,
                MonsterInstance.nextAvailableToken(monsterInstances).intValue()
        );
    }

    private List<MonsterInstance> populateMonsterInstances(List<Integer> tokens) {
        List<MonsterInstance> results = new ArrayList<>();

        for (Integer token : tokens) {
            MonsterInstance monsterInstance = MonsterInstance.create(10, false, room, monster);
            monsterInstance.setToken(token);
            results.add(monsterInstance);
        }

        return results;
    }
}