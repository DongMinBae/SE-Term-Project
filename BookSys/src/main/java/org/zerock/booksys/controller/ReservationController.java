package org.zerock.booksys.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.booksys.dto.CustomerDTO;
import org.zerock.booksys.dto.ReservationDTO;
import org.zerock.booksys.dto.SelectDayDTO;
import org.zerock.booksys.service.CustomerService;
import org.zerock.booksys.service.ReservationService;

import javax.validation.Valid;

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

    @PostMapping("/selectmenu") // 미완성
    public String selectMenuPost(@Valid CustomerDTO customerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
       log.info("reservation selectMenu post....");

       return null;
    }

    @GetMapping("selectday")
    public void selectDay(){
        log.info("select day page");
    }

    @PostMapping("/selectday")
    public String selectDayPost(SelectDayDTO reservationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("reservation selectDay Post ....");

        if(bindingResult.hasErrors()){
            log.info("has errors....");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/reservation/selectday";
        }

        log.info("Post form selectMenu : " + reservationDTO);

        //Long rno = reservationService.register(reservationDTO);

        //redirectAttributes.addFlashAttribute("result", rno);

        return "redirect:/reservation/selectmenu";
    }



}
