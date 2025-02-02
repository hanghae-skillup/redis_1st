import com.example.redis.reserve.`in`.dto.MovieReserveRequestDto
import com.example.redis.reserve.`in`.dto.MovieReserveSeatRequestDto
import com.example.redis.theater.Seat

class MovieReserveRequestDtoFactory {
    companion object {
        val SCREENING_69_SEAT = mutableListOf(
            MovieReserveSeatRequestDto(296, "A", "1"),
            MovieReserveSeatRequestDto(291, "A", "2"),
            MovieReserveSeatRequestDto(286, "A", "3"),
            MovieReserveSeatRequestDto(281, "A", "4"),
            MovieReserveSeatRequestDto(276, "A", "5"),
            MovieReserveSeatRequestDto(297, "B", "1"),
            MovieReserveSeatRequestDto(292, "B", "2"),
            MovieReserveSeatRequestDto(287, "B", "3"),
            MovieReserveSeatRequestDto(282, "B", "4"),
            MovieReserveSeatRequestDto(277, "B", "5"),
            MovieReserveSeatRequestDto(298, "C", "1"),
            MovieReserveSeatRequestDto(293, "C", "2"),
            MovieReserveSeatRequestDto(288, "C", "3"),
            MovieReserveSeatRequestDto(283, "C", "4"),
            MovieReserveSeatRequestDto(278, "C", "5"),
            MovieReserveSeatRequestDto(299, "D", "1"),
            MovieReserveSeatRequestDto(294, "D", "2"),
            MovieReserveSeatRequestDto(289, "D", "3"),
            MovieReserveSeatRequestDto(284, "D", "4"),
            MovieReserveSeatRequestDto(279, "D", "5"),
            MovieReserveSeatRequestDto(300, "E", "1"),
            MovieReserveSeatRequestDto(295, "E", "2"),
            MovieReserveSeatRequestDto(290, "E", "3"),
            MovieReserveSeatRequestDto(285, "E", "4"),
            MovieReserveSeatRequestDto(280, "E", "5"),
        )

        val SCREENING_70_SEAT = mutableListOf(
            MovieReserveSeatRequestDto(471, "A", "1"),
            MovieReserveSeatRequestDto(466, "A", "2"),
            MovieReserveSeatRequestDto(461, "A", "3"),
            MovieReserveSeatRequestDto(456, "A", "4"),
            MovieReserveSeatRequestDto(451, "A", "5"),
            MovieReserveSeatRequestDto(472, "B", "1"),
            MovieReserveSeatRequestDto(467, "B", "2"),
            MovieReserveSeatRequestDto(462, "B", "3"),
            MovieReserveSeatRequestDto(457, "B", "4"),
            MovieReserveSeatRequestDto(452, "B", "5"),
            MovieReserveSeatRequestDto(473, "C", "1"),
            MovieReserveSeatRequestDto(468, "C", "2"),
            MovieReserveSeatRequestDto(463, "C", "3"),
            MovieReserveSeatRequestDto(458, "C", "4"),
            MovieReserveSeatRequestDto(453, "C", "5"),
            MovieReserveSeatRequestDto(474, "D", "1"),
            MovieReserveSeatRequestDto(469, "D", "2"),
            MovieReserveSeatRequestDto(464, "D", "3"),
            MovieReserveSeatRequestDto(459, "D", "4"),
            MovieReserveSeatRequestDto(454, "D", "5"),
            MovieReserveSeatRequestDto(475, "E", "1"),
            MovieReserveSeatRequestDto(470, "E", "2"),
            MovieReserveSeatRequestDto(465, "E", "3"),
            MovieReserveSeatRequestDto(460, "E", "4"),
            MovieReserveSeatRequestDto(455, "E", "5"),
        )

        val SCREENING_71_SEAT = mutableListOf(
            MovieReserveSeatRequestDto(71, "A", "1"),
            MovieReserveSeatRequestDto(66, "A", "2"),
            MovieReserveSeatRequestDto(61, "A", "3"),
            MovieReserveSeatRequestDto(56, "A", "4"),
            MovieReserveSeatRequestDto(51, "A", "5"),
            MovieReserveSeatRequestDto(72, "B", "1"),
            MovieReserveSeatRequestDto(67, "B", "2"),
            MovieReserveSeatRequestDto(62, "B", "3"),
            MovieReserveSeatRequestDto(57, "B", "4"),
            MovieReserveSeatRequestDto(52, "B", "5"),
            MovieReserveSeatRequestDto(73, "C", "1"),
            MovieReserveSeatRequestDto(68, "C", "2"),
            MovieReserveSeatRequestDto(63, "C", "3"),
            MovieReserveSeatRequestDto(58, "C", "4"),
            MovieReserveSeatRequestDto(53, "C", "5"),
            MovieReserveSeatRequestDto(74, "D", "1"),
            MovieReserveSeatRequestDto(69, "D", "2"),
            MovieReserveSeatRequestDto(64, "D", "3"),
            MovieReserveSeatRequestDto(59, "D", "4"),
            MovieReserveSeatRequestDto(54, "D", "5"),
            MovieReserveSeatRequestDto(75, "E", "1"),
            MovieReserveSeatRequestDto(70, "E", "2"),
            MovieReserveSeatRequestDto(65, "E", "3"),
            MovieReserveSeatRequestDto(60, "E", "4"),
            MovieReserveSeatRequestDto(55, "E", "5"),
        )

        fun fromScreening69(userId: Long, size: Int = 25):MutableList<MovieReserveRequestDto> {
            val reservations = mutableListOf<MovieReserveRequestDto>()
            repeat(size) {
                index -> reservations.add(
                            MovieReserveRequestDto(
                            movieId = 1,
                            userId = userId,
                            screeningId = 69,
                            seats = mutableListOf(SCREENING_69_SEAT[index])
                        )
                )
            }
            return reservations
        }

        fun fromScreening70(userId: Long, size: Int = 25):MutableList<MovieReserveRequestDto> {
            val reservations = mutableListOf<MovieReserveRequestDto>()
            repeat(size) {
                    index -> reservations.add(
                        MovieReserveRequestDto(
                            movieId = 284,
                            userId = userId,
                            screeningId = 70,
                            seats = mutableListOf(SCREENING_70_SEAT[index])
                        )
                    )
            }
            return reservations
        }

        fun fromScreening71(userId: Long, size: Int = 25):MutableList<MovieReserveRequestDto> {
            val reservations = mutableListOf<MovieReserveRequestDto>()
            repeat(size) {
                    index -> reservations.add(
                        MovieReserveRequestDto(
                            movieId = 68,
                            userId = userId,
                            screeningId = 71,
                            seats = mutableListOf(SCREENING_71_SEAT[index])
                        )
                    )
            }
            return reservations
        }

    }
}