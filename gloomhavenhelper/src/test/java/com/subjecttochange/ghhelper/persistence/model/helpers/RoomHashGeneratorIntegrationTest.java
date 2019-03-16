package com.subjecttochange.ghhelper.persistence.model.helpers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class RoomHashGeneratorIntegrationTest {

    @Test
    public void whenHashGenerated_thenShouldBeNCharacters() {
        assertThat(RoomHashGenerator.newHash().length(), is(RoomHashGenerator.HASH_LENGTH));
    }
}