package com.subjecttochange.ghhelper.persistence.repository;

import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class RoomRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void whenFindByHash_thenReturnRoom() {
        // given
        Room room = Room.create(0, 0);
        entityManager.persist(room);
        entityManager.flush();

        // when
        Room found = roomRepository.findByHash(room.getHash()).orElseThrow(ResourceNotFoundException::new);

        // then
        assertThat(found.getHash(), is(room.getHash()));
    }
}