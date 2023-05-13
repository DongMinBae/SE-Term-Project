package org.zerock.booksys.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.zerock.booksys.domain.Customer;
import org.zerock.booksys.domain.Reservation;
import org.zerock.booksys.dto.ArrivalTime;
import org.zerock.booksys.dto.CustomerDTO;
import org.zerock.booksys.dto.ReservationDTO;
import org.zerock.booksys.repository.ReservationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    @PersistenceContext
    private EntityManager entityManager;

    private final ModelMapper modelMapper;
    private final ReservationRepository reservationRepository;

    @Override
    public Long register(ReservationDTO reservationDTO) {
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
        Long rno = reservationRepository.save(reservation).getRno();
        log.info("Insert rno : " + rno);
        return rno;
    }

    /**
     * 해당 스케쥴이 이미 예약되어 있는지 확인합니다.
     * @param tableNumber
     * @param time
     * @return
     */
    @Override
    public boolean CheckScheduleOccupied(int tableNumber, ArrivalTime time)
    {
        List<Reservation> list = reservationRepository.findByTableNumberAndArrivalTime(tableNumber,time);
        if(list.isEmpty()) return false;
        for(Reservation r : list)
        {
            if(r.getArrivalTime() == time && r.getTableNumber() == tableNumber)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(String cid, int tableNumber, ArrivalTime time) {
        List<Reservation> list = reservationRepository.findReservation(cid);
        Reservation del = null;
        for(Reservation r : list)
        {
            if(r.getArrivalTime() == time && r.getTableNumber() == tableNumber)
            {
                del = r;
            }
        }
        this.reservationRepository.delete(del);
    }


    @Override
    public void modify(ReservationDTO reservationDTO) {
        Optional<Reservation> result = reservationRepository.findById(reservationDTO.getRno());
        Reservation reservation = result.orElseThrow();


    }

    @Override
    public void modifyArrivalTime(Long rno,int time)
    {
        if(time == -1)
            reservationRepository.modifyArrivalTime(rno);
        else
            reservationRepository.modifyArrivalTime(rno,time);
    }

    @Override
    public void remove(Long rno)
    {
        reservationRepository.deleteById(rno);
    }

    @Override
    public void remove(String cid)
    {
        reservationRepository.deleteReservation(cid);
    }

    @Override
    public ReservationDTO readOne(Long rno) {
        Optional<Reservation> result = reservationRepository.findById(rno);

        Reservation reservation = result.orElseThrow();

        ReservationDTO reservationDTO = modelMapper.map(reservation, ReservationDTO.class);

        return reservationDTO;
    }
}
