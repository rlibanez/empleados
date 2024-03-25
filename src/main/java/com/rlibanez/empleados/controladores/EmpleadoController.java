package com.rlibanez.empleados.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.rlibanez.empleados.modelos.Empleado;
import com.rlibanez.empleados.servicios.EmpleadoService;

@Controller
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping({"/", "empleado/list"})
	public String listado(Model model) {
		model.addAttribute("listaEmpleados", empleadoService.findAll());
		return "list";
	}
	
	@GetMapping("/empleado/new")
	public String nuevoEmpleadoForm(Model model) {
		model.addAttribute("empleadoForm", new Empleado());
		return "form";
	}
	
	@PostMapping("/empleado/new/submit")
	public String nuevoEmpleadoSubmit(@ModelAttribute("empleadoForm") Empleado nuevoEmpleado) {
		empleadoService.add(nuevoEmpleado);
		return "redirect:/empleado/list";
	}

}
