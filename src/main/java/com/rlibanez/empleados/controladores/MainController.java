package com.rlibanez.empleados.controladores;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

//	@GetMapping("/")
//	public String welcome() {
//		return "index";
//	}

	// Saludo personalizado con Spring
//	@GetMapping("/")
//	public String welcome2(@RequestParam(name="name", required=false, defaultValue= "Mundo") String name, Model model) {
//		model.addAttribute("nombre", name);
//		return "index";
//	}
	
	// Saludo personalizado con Java
	@GetMapping("/")
	public String welcome3(@RequestParam("name") Optional<String> name, Model model) {
		model.addAttribute("nombre", name.orElse("Mundo"));
		return "index";
	}

}
