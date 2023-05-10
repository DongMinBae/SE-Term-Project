package org.zerock.booksys.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.booksys.domain.Customer;
import org.zerock.booksys.domain.Reservation;

import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class ReservationRepositoryTests {
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void TestInsert(){
        Reservation reservation = Reservation.builder()
                .customer(
                        Customer.builder().cno(1L).build()
                )
                .build();

        Reservation result = reservationRepository.save(reservation);

        log.info("result : " + result.getRno());
    }
}
