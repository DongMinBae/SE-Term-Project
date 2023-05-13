package org.zerock.booksys.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();

        if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/reservation/mainpage";
        } else {
            return "redirect:/reservation/mainpage";
        }
    }
}

