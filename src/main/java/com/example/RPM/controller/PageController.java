package com.example.RPM.controller;

import com.example.RPM.service.interfaces.RPMMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    private RPMMethods rpmMethods;

    @Autowired
    public void setRpmMethods(RPMMethods rpmMethods) {
        this.rpmMethods = rpmMethods;
    }

    @GetMapping("/")
    public String page(Model model) {
        return "index";
    }

    @GetMapping("/calculator")
    public String calculatorPage(Model model) {
        return "calculator";
    }

    @PostMapping("/calculator")
    public String calculate(@RequestParam String input, Model model) {

        if (input.length() <= 1) {
            model.addAttribute("lengthError", " The math expression should have more than 1 symbol");
            return "calculator";
        }
        if (!rpmMethods.isMathExpression(input)) {
            model.addAttribute("inputError", "The math expression shouldn't have letters and unknown symbols");
            return "calculator";
        }
        String expression = rpmMethods.getExpression(input);
        double result = rpmMethods.calculate(input);
        model.addAttribute("input", input);
        model.addAttribute("result", result);
        model.addAttribute("expression", expression);
        return null;
    }
}
