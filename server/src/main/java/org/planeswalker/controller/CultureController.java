package org.planeswalker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/culture")
public class CultureController {

    @GetMapping("/celebrity")
    public String celebrity(){
        return "celebrity";
    }

    @GetMapping("/ruins")
    public String ruins(){
        return "ruins";
    }

}
