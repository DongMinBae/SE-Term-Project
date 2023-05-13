package org.zerock.booksys.service;

import org.zerock.booksys.domain.Customer;
import org.zerock.booksys.dto.ArrivalTime;
import org.zerock.booksys.dto.CustomerDTO;
import org.zerock.booksys.dto.ReservationDTO;

public interface ReservationService {

    Long register(ReservationDTO reservationDTO);

    boolean CheckScheduleOccupied(int tableNumber, ArrivalTime time);
    void remove(String cid, int tableNumber, ArrivalTime time);

    void modify(ReservationDTO reservationDTO);
    void modifyArrivalTime(Long rno,int time);

    void remove(Long rno);

    /**
     * 사용자 아이디로 해당 예약을 삭제합니다.
     * @param cid
     */
    void remove(String cid);

    ReservationDTO readOne(Long rno);
}
