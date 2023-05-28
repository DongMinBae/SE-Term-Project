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
    @Query(value = "SELECT * FROM reservation m WHERE m.rno = :rno", nativeQuery = true)
    List<Reservation> findReservation(@Param("rno") Long rno);
    @Query(value = "SELECT * FROM reservation", nativeQuery = true)
    List<Reservation> findAllReservation();
    @Query(value = "DELETE FROM reservation WHERE cid = :cid", nativeQuery = true)
    void deleteReservation(@Param("cid") String cid);

    @Query(value = "DELETE FROM reservation WHERE rno = :rno", nativeQuery = true)
    void deleteReservation(@Param("rno") Long rno);

    @Query(value = "SELECT * FROM reservation m WHERE m.selected_date = (SELECT a.selected_date FROM reservation a WHERE a.rno = :rno)", nativeQuery = true)
    List<Reservation> findReservationSameDate(@Param("rno") Long rno);

    @Query(value = "SELECT * FROM reservation m WHERE m.cid = :cid AND m.selected_date = (SELECT a.selected_date FROM reservation a WHERE a.rno = :rno)", nativeQuery = true)
    List<Reservation> findReservationSameDate(@Param("cid") String cid,@Param("rno") Long rno);

    @Transactional
    @Modifying
    @Query(value = "Update reservation m set m.arrivaltime = :arrivaltime where m.rno = :rno", nativeQuery = true)
    void modifyArrivalTime(@Param("rno") Long rno, @Param("arrivaltime") int arrivaltime);

    @Transactional
    @Modifying
    @Query(value = "Update reservation m set m.tableno = :tableno where m.rno = :rno", nativeQuery = true)
    void modifyTableNo(@Param("rno") Long rno, @Param("tableno") int tableno);

    @Transactional
    @Modifying
    @Query(value = "UPDATE reservation m SET m.tableno = 0, m.arrivaltime = NULL where m.cid = :cid AND m.tableno = :tableno AND m.arrivaltime = :arrivaltime", nativeQuery = true)
    void removeReservation(@Param("cid") String cid,@Param("tableno") int tableno, @Param("arrivaltime") int arrivaltime);


    List<Reservation> findByTableNumberAndArrivalTime(int tableNumber,ArrivalTime arrivalTime);

}
