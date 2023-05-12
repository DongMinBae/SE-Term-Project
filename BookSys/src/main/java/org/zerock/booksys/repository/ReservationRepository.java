package org.zerock.booksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.booksys.domain.Customer;
import org.zerock.booksys.domain.Reservation;
import org.zerock.booksys.dto.ArrivalTime;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservation m WHERE m.cid = :cid", nativeQuery = true)
    List<Reservation> findReservation(@Param("cid") String cid);
    @Query(value = "DELETE FROM reservation WHERE cid = :cid", nativeQuery = true)
    void deleteReservation(@Param("cid") String cid);

    List<Reservation> findByTableNumberAndArrivalTime(int tableNumber,ArrivalTime arrivalTime);

}
