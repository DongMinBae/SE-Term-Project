package org.zerock.booksys.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.booksys.domain.Customer;
import org.zerock.booksys.dto.ArrivalTime;
import org.zerock.booksys.dto.CustomerDTO;
import org.zerock.booksys.dto.ReservationDTO;
import org.zerock.booksys.dto.SelectDayDTO;
import org.zerock.booksys.service.CustomerService;
import org.zerock.booksys.service.ReservationService;

@Controller
@RequestMapping("/reservation")
@Log4j2
@RequiredArgsConstructor
public class ReservationController {

    private final static String RESERVATION_ADD = "add";
    private final static String RESERVATION_REMOVE = "remove";

    private final CustomerService customerService;
    private final ReservationService reservationService;


    @GetMapping("/mainpage")
    public void mainPage(){
        log.info("main page");
    }

    @GetMapping("/selectmenu")
    public void selectMenu()
    {
        //ReservationDTO dto = new ReservationDTO(4L,1,null, 1,ArrivalTime.TIME_08_10,"hello");
        //reservationService.register(dto);
        log.info("menu select page");
    }

    @PostMapping("/selectmenu")
    public void selectMenuPost(MultipartHttpServletRequest req) throws Exception {
        String test = req.getParameter("id");
        String cmd = req.getParameter("cmd");

        String userId = "asdf"; // 사용자 아이디
        ArrivalTime[] time = ArrivalTime.values();

        switch (cmd) {
            case RESERVATION_ADD:
                String[] s = test.split("_");
                int tableNumber = Integer.parseInt(s[0]);
                int arrivalTime = Integer.parseInt(s[1]);

                if(this.reservationService.CheckScheduleOccupied(userId,tableNumber,time[arrivalTime]))
                    log.info("Occupied");
                else
                {
                    ReservationDTO dto = ReservationDTO
                            .builder()
                            .customerID(userId)
                            .arrivalTime(time[arrivalTime])
                            .tableNumber(tableNumber)
                            .number(4)
                            .selectedDate(null)
                            .build();

                    this.reservationService.register(dto);

                    log.info("ADD Reservation");
                    log.info("arrivalTime ->" + time[arrivalTime]);
                    log.info("tableNumber ->" + tableNumber);
                }

                break;
            case RESERVATION_REMOVE:
                String[] s2 = test.split("_");
                int tableNumber2 = Integer.parseInt(s2[0]);
                int arrivalTime2 = Integer.parseInt(s2[1]);
                this.reservationService.remove(userId,tableNumber2,time[arrivalTime2]);
                log.info("REMOVE Reservation");
                log.info("arrivalTime ->" + time[arrivalTime2]);
                log.info("tableNumber ->" + tableNumber2);
                break;
        }
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
