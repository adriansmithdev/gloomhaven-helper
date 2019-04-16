package com.subjecttochange.ghhelper.persistence.model.orm;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.*;

@ActiveProfiles("test")
public class StatTest {

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void create() {
        Stat stat = Stat.create("testName", "test Tooltip");
        assertEquals(
                "Name should be \"testName\"",
                "testName",
                stat.getName()
        );
    }


    @Test
    public void updateStat() {
        Stat testStat = Stat.create("testName", "test Tooltip");
        Stat updateStat = Stat.create("testUpdatedName", "test Updated Tooltip");
        testStat.updateStat(updateStat);
        assertEquals(
                "Name should be \"test Updated Tooltip\"",
                "test Updated Tooltip",
                testStat.getTooltip()
        );
    }
}