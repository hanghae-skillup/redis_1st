package com.movie.storage.movie.bulkInsert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {BulkInsertSchedule.class, BulkInsertMovie.class, BulkInsertScreen.class})
class BulkInsertTest {

    @Autowired
    private BulkInsertSchedule bulkInsertSchedule;

    @Autowired
    private BulkInsertScreen bulkInsertScreen;

    @Autowired
    private BulkInsertMovie bulkInsertMovie;


    @Test
    void bulkInsertSchedule() {
        // given

        // when
        bulkInsertSchedule.bulkInsertSchedules(3200, 80);

        // then
    }

    @Test
    void bulkInsertScreen() {
        // given

        // when
        bulkInsertScreen.bulkInsertScreens(400, 40);

        // then
    }

    @Test
    void bulkInsertMovie() {
        // given

        // when
        bulkInsertMovie.insertMovie(500, 50);

        // then
    }

    @Test
    void bulkInsertTheater() {
        // given

        // when
        bulkInsertScreen.bulkInsertTheater(40, 10);

        // then
    }
  
}