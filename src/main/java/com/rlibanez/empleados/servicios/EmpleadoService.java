package com.rlibanez.empleados.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rlibanez.empleados.modelos.Empleado;

import jakarta.annotation.PostConstruct;

@Service
public class EmpleadoService {

	private List<Empleado> empleadoRepository = new ArrayList<>();

	public Empleado add(Empleado e) {
		empleadoRepository.add(e);
		return e;
	}

	public List<Empleado> findAll() {
		return empleadoRepository;
	}

	@PostConstruct
	public void init() {
		empleadoRepository
				.addAll(Arrays.asList(new Empleado(1, "Antonio García", "antonio.garcia@openwebinars.net", "942334421"),
						new Empleado(2, "María López", "maria.lopez@openwebinars.net", "916547890"),
						new Empleado(3, "Ángel Antúnez", "angel.antunez@openwebinars.net", "954000000")));
	}

}
