package com.gft.academy.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gft.academy.banco.model.ClienteDevedor;
import com.gft.academy.banco.repository.ClienteDevedorRepository;

@Controller
public class ClienteDevedorController {

	@Autowired
	ClienteDevedorRepository repository;
	
	@GetMapping("/teste")
	public String listarDevedores(Model model) {
		model.addAttribute("listaDevedores", repository.findAll());
		return "home";
	}
	
}
