package org.zerock.booksys.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.booksys.dto.ReservationDTO;
import org.zerock.booksys.dto.SelectDayDTO;

import java.sql.Date;

@SpringBootTest
@Log4j2
public class ReservationServiceTests {

    @Autowired
    private ReservationService reservationService;

    @Test
    public void testSelDTOToReDTO(){
        SelectDayDTO selectDayDTO = SelectDayDTO.builder()
                .name("천인국")
                .number(2)
                .phoneNumber("01012345678")
                .selectedDate(Date.valueOf("2022-05-22"))
                .selectTime("오전")
                .build();

        ReservationDTO reservationDTO = selectDayDTO.toReservationDTO();
        reservationDTO.setCustomerID("asdf");

        Long rno = reservationService.register(reservationDTO);

        log.info("rno : " + rno);
    }
}
