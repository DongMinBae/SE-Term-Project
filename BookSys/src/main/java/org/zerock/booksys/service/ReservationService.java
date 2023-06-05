package org.zerock.booksys.service;

import org.zerock.booksys.domain.Customer;
import org.zerock.booksys.dto.ArrivalTime;
import org.zerock.booksys.dto.CustomerDTO;
import org.zerock.booksys.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {

    Long register(ReservationDTO reservationDTO);

    boolean CheckScheduleOccupied(int tableNumber, ArrivalTime time,Long rno);
    List<String> getAvailableSchedule(Long rno);
    List<String> getModifiableSchedule(String cid,Long rno);
    String getSchedule(Long rno);

    String getReservationListTOJSON();

    void remove(String cid, int tableNumber, ArrivalTime time);

    void modify(ReservationDTO reservationDTO);
    void modifySchedule(Long rno,int time,int table);

    void removeSchedule(String cid, int table, ArrivalTime time);

    void remove(Long rno);

    /**
     * 사용자 아이디로 해당 예약을 삭제합니다.
     * @param cid
     */
    void remove(String cid);

    ReservationDTO readOne(Long rno);
}
