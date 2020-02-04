package org.planeswalker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/culture")
public class CultureController {

    /**
     * 名人故事
     * @return
     */
    @GetMapping("/celebrity")
    public String celebrity(){
        return "celebrity";
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
