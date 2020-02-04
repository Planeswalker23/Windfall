package org.planeswalker.controller;

import org.planeswalker.pojo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(HttpServletRequest request,
                          Model model){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }

        return "about-me";
    }
}
