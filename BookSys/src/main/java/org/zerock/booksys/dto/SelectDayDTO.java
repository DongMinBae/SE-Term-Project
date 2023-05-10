package org.zerock.booksys.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectDayDTO {
    private String name;

    private int number;

    private String phoneNumber;

    private Date selectedDate;

    private String selectTime;

    public ReservationDTO toReservationDTO(){
        ReservationDTO reservationDTO = ReservationDTO.builder()
                .number(number)
                .selectedDate(selectedDate)
                .build();

        return reservationDTO;
    }



}
