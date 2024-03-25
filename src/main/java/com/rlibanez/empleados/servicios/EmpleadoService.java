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

	public Empleado findById(Long id) {
		Empleado empleado = null;
		int indice = 0;
		while (indice < empleadoRepository.size() && empleado == null) {
			if (empleadoRepository.get(indice).getId() == id) {
				empleado = empleadoRepository.get(indice);
			}
			indice++;
		}
		return empleado;
	}

	public Empleado editEmpleado(Empleado empleadoEditado) {
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < empleadoRepository.size()) {
			if (empleadoRepository.get(i).getId() == empleadoEditado.getId()) {
				encontrado = true;
				empleadoRepository.remove(i);
				empleadoRepository.add(i, empleadoEditado);
			} else {
				i++;
			}
		}
		if (!encontrado)
			empleadoRepository.add(empleadoEditado);
		return empleadoEditado;
	}

	@PostConstruct
	public void init() {
		empleadoRepository
				.addAll(Arrays.asList(new Empleado(1, "Antonio García", "antonio.garcia@openwebinars.net", "942334421"),
						new Empleado(2, "María López", "maria.lopez@openwebinars.net", "916547890"),
						new Empleado(3, "Ángel Antúnez", "angel.antunez@openwebinars.net", "948997755")));
	}

}
