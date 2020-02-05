package org.planeswalker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CultureController {

    /**
     * 名人故事
     * @return
     */
    @GetMapping("/cultural")
    public String cultural(){
        return "cultural";
    }

    /**
     * 古代遗迹
     * @return
     */
    @GetMapping("/ruins")
    public String ruins(){
        return "ruins";
    }

}
