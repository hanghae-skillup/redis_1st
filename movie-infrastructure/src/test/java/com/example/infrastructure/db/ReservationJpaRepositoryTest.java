package com.example.infrastructure.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.domain.model.entity.Member;
import com.example.domain.model.entity.Movie;
import com.example.domain.model.entity.Reservation;
import com.example.domain.model.entity.Screening;
import com.example.domain.model.entity.Theater;
import com.example.domain.model.valueObject.ContentRating;
import com.example.domain.model.valueObject.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DisplayName("[JPA 테스트] 예약 리포지토리")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB에서 테스트
@DataJpaTest
class ReservationJpaRepositoryTest {

    @Autowired
    private ReservationJpaRepository reservationJpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Reservation reservation;
    private Screening screening;
    private Member member;

    @BeforeEach
    void setUp() {
        member = Member.of("Test User", "testuser@email.net");
        Movie movie = Movie.of("Test Movie", ContentRating.ADULT, LocalDate.of(2024, 12, 25),
                "http://test.com/image01", 66, Genre.COMEDY);
        Theater theater = Theater.of("Test Theater");
        screening = Screening.of(LocalTime.of(13, 15, 0), movie, theater);

        // 개별 엔티티 먼저 영속화
        entityManager.persist(member);
        entityManager.persist(movie);
        entityManager.persist(theater);
        entityManager.persist(screening);
        entityManager.flush(); // DB에 반영

        // Reservation 생성 후 저장
        reservation = Reservation.of(screening, member);
        reservationJpaRepository.save(reservation);
        entityManager.flush();
        entityManager.clear();
    }

    @DisplayName("예약을 저장하고 조회할 수 있어야 한다.")
    @Test
    void givenReservation_whenSaveAndFindById_thenReturnReservation() {
        // Given
        Optional<Reservation> foundReservation = reservationJpaRepository.findById(reservation.getId());

        // When & Then
        assertThat(foundReservation).isPresent();
        assertThat(foundReservation.get().getMember().getName()).isEqualTo("Test User");
    }

    @DisplayName("특정 상영의 특정 회원 예약 수를 반환할 수 있어야 한다.")
    @Test
    void givenScreeningAndMember_whenCountReservations_thenReturnCount() {
        // Given
        reservationJpaRepository.save(reservation);
        entityManager.flush();
        entityManager.clear();

        // When
        int count = reservationJpaRepository.countByScreeningAndMember(screening, member);

        // Then
        assertThat(count).isEqualTo(1);
    }

    @DisplayName("모든 예약을 삭제할 수 있어야 한다.")
    @Test
    void givenReservations_whenDeleteAll_thenAllReservationsAreDeleted() {
        // Given
        reservationJpaRepository.save(reservation);
        reservationJpaRepository.save(reservation);
        entityManager.flush();
        entityManager.clear();

        // When
        reservationJpaRepository.deleteAll();
        long count = reservationJpaRepository.countByScreeningAndMember(screening, member);

        // Then
        assertThat(count).isEqualTo(0);
    }

}
