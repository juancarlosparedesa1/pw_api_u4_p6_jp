package com.edu.uce.pw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.uce.pw.repository.modelo.Materia;
import com.edu.uce.pw.service.IMateriaService;

@RestController
@RequestMapping(path = "/materias")

public class MateriaController {

	@Autowired
	private IMateriaService materiaService;

	// http://localhost:8080/API/v1.0/Matricula/materias/guardar
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Materia> guardar(@RequestBody Materia materia) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("mensaje_200", "Materia insertada");
		materiaService.ingresar(materia);
		return new ResponseEntity<Materia>(materia, headers, 200);
	}

	// http://localhost:8080/API/v1.0/Matricula/materias/actualizar/
	// Nivel1:http://localhost:8080/API/v1.0/Matricula/materias/1

	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Materia> actualizar(@PathVariable Integer id, @RequestBody Materia materia) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("mensaje_238", "Materia actualizada");
		Materia mater = materiaService.buscar(id);
		if (mater != null) {
			materia.setId(id);
			materiaService.actualizar(materia);
		}
		// return ResponseEntity.status(238).body(materia);

		return new ResponseEntity<Materia>(materia, headers, 238);
	}

	// http://localhost:8080/API/v1.0/Matricula/materias/actualizarParcial
	// Nivel 1: http://localhost:8080/API/v1.0/Matricula/materias/4

	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Materia> actualizarParcial(@PathVariable Integer id, @RequestBody Materia materia) {
		Materia mater = materiaService.buscar(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("mensaje_239", "Materia actuailzada pacialmente");

		if (mater != null) {
			if (materia.getNombre() != null) {
				mater.setNombre(materia.getNombre());
			}

			if (materia.getNumeroCreditos() != null) {
				mater.setNumeroCreditos(materia.getNumeroCreditos());
			}
			materiaService.actualizar(mater);
		}

		// return ResponseEntity.status(239).body(materia);
		return new ResponseEntity<Materia>(materia, headers, 239);
	}

	// http://localhost:8080/API/v1.0/Matricula/materias/borrar
	// http://localhost:8080/API/v1.0/Matricula/materias/borrar/1
	// http://localhost:8080/API/v1.0/Matricula/materias/borrar/2
	// http://localhost:8080/API/v1.0/Matricula/materias/borrar/3
	// ......

	// Nivel 1: http://localhost:8080/API/v1.0/Matricula/materias/3

	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> borrar(@PathVariable Integer id) {
		materiaService.borrar(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("mensaje_240", "Materia borrada");
		// return ResponseEntity.status(240).body("Borrado");

		return new ResponseEntity<String>("Borrado", headers, 240);
	}

	// http://localhost:8080/API/v1.0/Matricula/materias/1

	@GetMapping(path = "/{id}")
	public ResponseEntity<Materia> buscar(@PathVariable Integer id) {
		Materia m = materiaService.buscar(id);

		HttpHeaders headers = new HttpHeaders();
		// return ResponseEntity.status(236).body(m);

		return new ResponseEntity<Materia>(m, headers, 236);
	}

}