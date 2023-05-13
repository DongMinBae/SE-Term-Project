package org.zerock.booksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.booksys.domain.Customer;
import org.zerock.booksys.domain.Reservation;
import org.zerock.booksys.dto.ArrivalTime;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservation m WHERE m.cid = :cid", nativeQuery = true)
    List<Reservation> findReservation(@Param("cid") String cid);
    @Query(value = "DELETE FROM reservation WHERE cid = :cid", nativeQuery = true)
    void deleteReservation(@Param("cid") String cid);

    @Transactional
    @Modifying
    @Query(value = "Update reservation m set m.arrivaltime = :arrivaltime where m.rno = :rno", nativeQuery = true)
    void modifyArrivalTime(@Param("rno") Long rno, @Param("arrivaltime") int arrivaltime);

    @Transactional
    @Modifying
    @Query(value = "Update reservation m set m.arrivaltime = NULL where m.rno = :rno", nativeQuery = true)
    void modifyArrivalTime(@Param("rno") Long rno);

    List<Reservation> findByTableNumberAndArrivalTime(int tableNumber,ArrivalTime arrivalTime);

}
