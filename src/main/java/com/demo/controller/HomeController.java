package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")//默认首页
    public String home() {
        System.out.println("有没有进来home");
        return "index";
    }

}
