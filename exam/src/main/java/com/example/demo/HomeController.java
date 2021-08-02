package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.SearchDTO;

@Controller
public class HomeController {
	
	@Autowired private HomeService service;
	
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("locationList", service.selLocationCodeList());
		return "main";
	}
	
	@PostMapping("/result")
	public void result(SearchDTO param, Model model) {
		model.addAttribute("resultList", service.selResultList(param));
	}
	
}
