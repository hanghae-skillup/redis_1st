package com.movie.storage.movie.bulkInsert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        BulkInsertSchedule.class, BulkInsertMovie.class,
        BulkInsertScreen.class, BulkInsertReservation.class
})
class BulkInsertTest {

    @Autowired
    private BulkInsertSchedule bulkInsertSchedule;

    @Autowired
    private BulkInsertScreen bulkInsertScreen;

    @Autowired
    private BulkInsertMovie bulkInsertMovie;

    @Autowired
    private BulkInsertReservation bulkInsertReservation;


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
        bulkInsertScreen.bulkInsertTheater(1_000, 100);

        // then
    }

    @Test
    void bulkInsertScreen() {
        // given

        // when
        bulkInsertScreen.bulkInsertScreens(10_000, 1_000);

        // then
    }

    @Test
    void bulkInsertSchedule() {
        // given

        // when
        bulkInsertSchedule.bulkInsertSchedules(100_000, 1_000);

        // then
    }

    @Test
    void bulkInsertReservation() {

        bulkInsertReservation.bulkInsertReservation(25, 25);

    }

}