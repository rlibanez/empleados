package com.rlibanez.empleados.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

//	@GetMapping("/")
//	public String welcome() {
//		return "index";
//	}

	@GetMapping("/")
	public String welcome2(Model model) {
		model.addAttribute("mensaje", "Hola a todos");
		return "index";
	}

}
