package org.zerock.booksys.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  임시 테스트 DTO
 */
@Data
@ToString
@RequiredArgsConstructor
class TestDTO
{
    private final String name;
    private final String num;
}

/**
 * 임시 테스트 컨트롤러
 */
@Controller
@Log4j2
public class TestController
{
    /**
     * /test
     */
    @RequestMapping("/test")
    public String test1(Model model)
    {
        String msg = "hello";
        log.log(Level.INFO,"msg: " + msg);
        model.addAttribute("msg",msg);

        TestDTO dto = new TestDTO("test","hello");
        log.log(Level.INFO,"dto: " + dto.toString());
        return "test";
    }

//    @PostMapping("/testform")
//    public String test2(TestDTO form)
//    {
//       log.log(Level.INFO,"Name:" + form.getName());
//       return "redirect:/test";
//    }
//
//    @GetMapping("/testform")
//    public String test3()
//    {
//        return "/testform";
//    }

}