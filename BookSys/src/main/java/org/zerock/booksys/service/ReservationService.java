package org.zerock.booksys.service;

import org.zerock.booksys.dto.CustomerDTO;
import org.zerock.booksys.dto.ReservationDTO;

public interface ReservationService {

    Long register(ReservationDTO reservationDTO);

    void modify(ReservationDTO reservationDTO);

    void remove(Long rno);

    ReservationDTO readOne(Long rno);
}
