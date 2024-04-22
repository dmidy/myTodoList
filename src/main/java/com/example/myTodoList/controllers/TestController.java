package com.example.myTodoList.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class TestController {
    @GetMapping ("/test")
    public ModelAndView getTest(){
        ModelAndView result = new ModelAndView("currentTest");
        result.addObject("hW", "Hello World!");
        return result;
    }
}
