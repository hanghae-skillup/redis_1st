package com.example.redis

import com.example.redis.movie.Reservation
import com.example.redis.movie.`in`.dto.MovieReserveRequestDto
import com.example.redis.movie.`in`.dto.SeatRequestDto
import com.example.redis.reserve.`in`.ReserveFacade
import com.example.redis.theater.Seat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.CyclicBarrier
import java.util.concurrent.Executors
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest
@Transactional
class ReserveFacadeTest {

    @Autowired
    private lateinit var reserveFacade: ReserveFacade

    @Test
    fun `같은 좌석에 동시 예매`() {
        val threadCount = 5
        val latch = CountDownLatch(threadCount)
        val concurrencyStart = CyclicBarrier(threadCount)
        val executors = Executors.newFixedThreadPool(threadCount)

        val movieId = 1L
        val seats = listOf(
            Seat(
                seatId = 296,
                seatRow = "A",
                seatCol = "1"
            ),
            Seat(
                seatId = 291,
                seatRow = "A",
                seatCol = "2"
            ),
            Seat(
                seatId = 286,
                seatRow = "A",
                seatCol = "3"
            ),
            Seat(
                seatId = 281,
                seatRow = "A",
                seatCol = "4"
            ),
            Seat(
                seatId = 276,
                seatRow = "A",
                seatCol = "5"
            )
        )

        val baseReservation = Reservation(
            screeningId = 69,
            movieId = 1,
            userId = 1,
            seats = seats.toMutableList()
        )

        val receiptIds = Array(threadCount) { UUID.randomUUID().toString() }
        val baseReservations = mutableListOf(
            baseReservation.copy(
                reserveReceiptId = receiptIds[0],
                userId = 1,
                seats = seats.subList(0, 1).toMutableList()
            ),
            baseReservation.copy(
                reserveReceiptId = receiptIds[1],
                userId = 2,
                seats = seats.subList(0, 2).toMutableList()
            ),
            baseReservation.copy(
                reserveReceiptId = receiptIds[2],
                userId = 3,
                seats = seats.subList(0, 3).toMutableList()
            ),
            baseReservation.copy(
                reserveReceiptId = receiptIds[3],
                userId = 4,
                seats = seats.subList(1, 4).toMutableList()
            ),
            baseReservation.copy(
                reserveReceiptId = receiptIds[4],
                userId = 5,
                seats = seats.subList(2, 4).toMutableList()
            )
        )

        repeat(threadCount) { index ->
            executors.execute {
                try {
                    concurrencyStart.await()
                    reserveFacade.reserve(movieId, baseReservations[index])
                } finally {
                    latch.countDown()
                }
            }
        }

        latch.await()
        executors.shutdown()

        // 모든 receiptId에 대한 예약 개수 확인
//        val receiptIdCounts = receiptIds.associateWith { receiptId ->
//            reserveFacade.findReserveCount(receiptId)
//        }
//
//        // 검증: 어떤 하나의 receiptId는 5개 예약, 나머지는 0개 예약
//        val targetReceiptId = receiptIdCounts.entries.find { it.value == 5 }?.key
//        assertNotNull(targetReceiptId, "Exactly one receiptId must have 5 reservations.")
//        receiptIdCounts.forEach { (receiptId, count) ->
//            if (receiptId == targetReceiptId) {
//                assertEquals(5, count, "Target receiptId $receiptId must have 5 reservations.")
//            } else {
//                assertEquals(0, count, "ReceiptId $receiptId must have 0 reservations.")
//            }
//        }
    }
}