package org.zerock.booksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.booksys.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
