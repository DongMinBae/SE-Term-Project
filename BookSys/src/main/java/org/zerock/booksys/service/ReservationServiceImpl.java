package org.zerock.booksys.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.booksys.domain.Reservation;
import org.zerock.booksys.dto.CustomerDTO;
import org.zerock.booksys.dto.ReservationDTO;
import org.zerock.booksys.repository.ReservationRepository;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ModelMapper modelMapper;

    private final ReservationRepository reservationRepository;

    @Override
    public Long register(ReservationDTO reservationDTO) {
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);

        Long rno = reservationRepository.save(reservation).getRno();

        log.info("Insert rno : " + rno);

        return rno;
    }

    @Override
    public void modify(ReservationDTO reservationDTO) {
        Optional<Reservation> result = reservationRepository.findById(reservationDTO.getRno());

        Reservation reservation = result.orElseThrow();
    }

    @Override
    public void remove(Long rno) {
        reservationRepository.deleteById(rno);
    }

    @Override
    public ReservationDTO readOne(Long rno) {
        Optional<Reservation> result = reservationRepository.findById(rno);

        Reservation reservation = result.orElseThrow();

        ReservationDTO reservationDTO = modelMapper.map(reservation, ReservationDTO.class);

        return reservationDTO;
    }
}
