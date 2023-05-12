package org.zerock.booksys.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Long rno;

    private int number;

    private Date selectedDate;

    private int tableNumber;

    private ArrivalTime arrivalTime;

    private String customerID;

}
