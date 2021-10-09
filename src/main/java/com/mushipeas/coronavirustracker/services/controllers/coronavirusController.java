package com.mushipeas.coronavirustracker.services.controllers;

import com.mushipeas.coronavirustracker.services.CoronavirusDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class coronavirusController {
    
    @Autowired
    CoronavirusDataService coronavirusDataService;
    
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("locationStats", coronavirusDataService.getAllLocationStats());
        model.addAttribute("totalReportedCases", coronavirusDataService.getTotalReportedCases());
        model.addAttribute("newCasesToday", coronavirusDataService.getNewCasesToday());
        return "index";
    } 
    
}