package kr.spartacodingclub.cinema.core.domain

data class Theater(
    val id: Long,
    val name: String,
    val seats: List<Seat> = generateSeats()
) {
    companion object {
        private fun generateSeats(): List<Seat> {
            return ('A'..'E').flatMap { row ->
                (1..5).map { col ->
                    Seat("$row$col")
                }
            }
        }
    }
}
