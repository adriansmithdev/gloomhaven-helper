package com.subjecttochange.ghhelper.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author subjecttochange
 * @version 1
 * Handles the default route for the web server
 */
@Controller
public class MainController {

    @Value("${spring.application.name}")
    private String appName;

    /**
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("appName", appName);
        return "index";
    }

    @Override
    public String toString() {
        return "MainController{" +
                "appName='" + appName + '\'' +
                '}';
    }
}