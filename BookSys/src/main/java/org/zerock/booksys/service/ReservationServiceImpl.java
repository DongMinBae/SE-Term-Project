package org.zerock.booksys.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public boolean CheckScheduleOccupied(int tableNumber, ArrivalTime time,Long rno)
    {
        List<Reservation> list = this.reservationRepository.findReservationSameDate(rno);
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
    public List<String> getAvailableSchedule(Long rno) {
        List<Reservation> list = this.reservationRepository.findReservationSameDate(rno);
        return list.stream().map(r -> {
            if(r.getTableNumber() == 0)
                return null;
            else
                return r.getTableNumber() +"_"+r.getArrivalTime().getValue();
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> getModifiableSchedule(String cid,Long rno) {
        List<Reservation> list = this.reservationRepository.findReservationSameDate(cid,rno);
        return list.stream().map(r -> {
            if(r.getTableNumber() == 0)
                return null;
            else
                return r.getTableNumber() +"_"+r.getArrivalTime().getValue();
        }).collect(Collectors.toList());
    }

    @Override
    public String getSchedule(Long rno) {
        List<Reservation> list = this.reservationRepository.findReservation(rno);

        if(list.isEmpty())
            return null;
        else
        {
            Reservation r = list.get(0);
            if(r.getTableNumber() == 0)
                return null;
            else
                return r.getTableNumber() +"_"+r.getArrivalTime().getValue();
        }
    }

    @Override
    public String getReservationListTOJSON() {
        Gson gson = new Gson();
        List<Reservation> list = reservationRepository.findAllReservation();

        JsonObject main = new JsonObject();

        for(Reservation r : list)
        {
            if(r.getSelectedDate() == null) continue;
            SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = newDtFormat.format(r.getSelectedDate());
            Customer c = r.getCustomer();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", c.getName());
            jsonObject.addProperty("phone", c.getPhoneNumber());
            jsonObject.addProperty("date",date);
            main.addProperty(r.getRno()+"", gson.toJson(jsonObject));
        }

        String jsonStr = gson.toJson(main);
        return jsonStr;
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
    public void modifySchedule(Long rno,int time,int table)
    {
        reservationRepository.modifyArrivalTime(rno,time);
        reservationRepository.modifyTableNo(rno,table);
    }

    @Override
    public void removeSchedule(String cid, int table, ArrivalTime time,Long rno)
    {
        reservationRepository.removeReservation(cid,table,time.getValue(),rno);
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
