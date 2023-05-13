package org.zerock.booksys.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.booksys.domain.Customer;
import org.zerock.booksys.domain.Reservation;
import org.zerock.booksys.dto.ReservationDTO;

import java.sql.Date;
import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class ReservationRepositoryTests {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void TestInsert(){
        Customer customer = Customer.builder()
                .cId("member@aaa.bbb")
                .cPassword(passwordEncoder.encode("1111"))
                .name("홍길동")
                .phoneNumber("01012345678")
                .build();

        Reservation reservation = Reservation.builder()
                .number(2)
                .selectedDate(Date.valueOf("2023-05-30"))
                .customer(customer)
                .build();

        reservationRepository.save(reservation);
    }
}
