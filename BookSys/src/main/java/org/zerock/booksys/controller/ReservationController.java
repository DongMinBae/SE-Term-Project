package org.zerock.booksys.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.booksys.service.ReservationService;

@Controller
@RequestMapping("/reservation")
@Log4j2
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/mainpage")
    public void mainPage(){
        log.info("main page");
    }

    @GetMapping("/selectmenu")
    public void selectMenu(){
        log.info("menu select page");
    }

    @GetMapping("selectday")
    public void selectDay(){
        log.info("select day page");
    }



}
