package org.zerock.booksys.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import java.util.List;

@Controller
@RequestMapping("/reservation")
@Log4j2
@RequiredArgsConstructor
public class ReservationController {

    private final static String RESERVATION_ARRIVAL_TIME_ADD = "add";
    private final static String RESERVATION_ARRIVAL_TIME_REMOVE = "remove";

    private final CustomerService customerService;
    private final ReservationService reservationService;


    @GetMapping("/mainpage")
    public void mainPage(){
        log.info("main page");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/modifyordelete")
    public void selectMenu(Model model)
    {
        Long rno = (Long) model.getAttribute("result");
        String cid = (String) model.getAttribute("cid");
        List<String> list = reservationService.getAvailableSchedule(rno);

        for(String a : list)
        {
            if(a == null) continue;
            model.addAttribute("m"+a,"occupied");
        }

        list = reservationService.getModifiableSchedule(cid,rno);

        for(String a : list)
        {
            if(a == null) continue;
            model.addAttribute("m"+a,"reserved");
        }

        log.info("menu select page");
    }

    @PostMapping("/modifyordelete")
    public void selectMenuPost(MultipartHttpServletRequest req) throws Exception
    {
        String test = req.getParameter("id");
        String cmd = req.getParameter("cmd");
        String cid = req.getParameter("cid");
        Long rno = Long.valueOf(req.getParameter("rno"));

        ArrivalTime[] time = ArrivalTime.values();

        switch (cmd)
        {
            case RESERVATION_ARRIVAL_TIME_ADD:
                String[] s = test.split("_");
                int tableNumber = Integer.parseInt(s[0]);
                int arrivalTime = Integer.parseInt(s[1]);

                if(this.reservationService.CheckScheduleOccupied(tableNumber,time[arrivalTime]))
                    log.info("Occupied");
                else
                {
                    this.reservationService.modifySchedule(rno,arrivalTime,tableNumber);

                    log.info("MODIFY ArrivalTime");
                    log.info("rno ->"+rno);
                    log.info("arrivalTime ->" + time[arrivalTime]);
                    log.info("tableNumber ->" + tableNumber);
                }
                break;
            case RESERVATION_ARRIVAL_TIME_REMOVE:
                String[] s2 = test.split("_");
                int tableNumber2 = Integer.parseInt(s2[0]);
                int arrivalTime2 = Integer.parseInt(s2[1]);

                this.reservationService.removeSchedule(cid,tableNumber2,time[arrivalTime2]);

                log.info("REMOVE ArrivalTime");
                log.info("arrivalTime ->" + time[arrivalTime2]);
                log.info("tableNumber ->" + tableNumber2);
                break;
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("selectday")
    public void selectDay(){
        log.info("select day page");
    }

    @PostMapping("/selectday")
    public String selectDayPost(SelectDayDTO selectDayDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("reservation selectDay Post ....");

        if(bindingResult.hasErrors()){
            log.info("has errors....");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/reservation/selectday";
        }

        log.info("Post form selectMenu : " + selectDayDTO);

        ReservationDTO reservationDTO = selectDayDTO.toReservationDTO();

        Long rno = reservationService.register(reservationDTO);

        redirectAttributes.addFlashAttribute("result", rno);
        redirectAttributes.addFlashAttribute("cid", reservationDTO.getCustomerID());
        return "redirect:/reservation/modifyordelete";
    }

    //test
    @GetMapping("/managereservation")
    public void manager(Model model){
        String jsonstr = this.reservationService.getReservationListTOJSON();
        //log.info(jsonstr);
        model.addAttribute("reservationjson",jsonstr);
        log.info("manager page");
    }

    @PostMapping("/managereservation")
    public void remove(MultipartHttpServletRequest req){
        String cmd = req.getParameter("cmd");
        String rno = req.getParameter("rno");
        log.info(cmd +"/"+ rno);
        this.reservationService.remove(Long.parseLong(rno));

    }

}
