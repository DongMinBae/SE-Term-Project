package org.zerock.booksys.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.booksys.domain.Customer;
import org.zerock.booksys.domain.Reservation;
import org.zerock.booksys.dto.ArrivalTime;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ReservationRepositoryTests {
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void TestInsert(){

        Reservation reservation = Reservation.builder()
                .customer(Customer.builder().cId("asdf").build())
                .arrivalTime(ArrivalTime.TIME_08_10)
                .number(1)
                .tableNumber(1)
                .build();

        Reservation result = reservationRepository.save(reservation);

        log.info("result : " + result.getRno());
    }
}
