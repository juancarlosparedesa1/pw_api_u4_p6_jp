package com.edu.uce.pw.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.uce.pw.repository.modelo.Estudiante;
import com.edu.uce.pw.service.IEstudianteService;
import com.edu.uce.pw.service.IMateriaService;
import com.edu.uce.pw.service.to.EstudianteTO;
import com.edu.uce.pw.service.to.MateriaTO;

@RestController
@RequestMapping(path = "/estudiantes")
public class EstudianteController {

	@Autowired
	private IEstudianteService estudianteService;

	@Autowired
	private IMateriaService iMateriaService;

	@PostMapping(produces = "application/json", consumes = "application/xml")
	public ResponseEntity<Estudiante> guardar(@RequestBody Estudiante est) {
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_201", "Estudiante guardado");
		this.estudianteService.ingresar(est);
		return new ResponseEntity<>(est, cabeceras, HttpStatus.OK);
	}

	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Estudiante> actualizar(@RequestBody Estudiante est, @PathVariable Integer id) {
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mesaje_238", "El estudiante fue actualizado");
		est.setId(id);
		this.estudianteService.actualizar(est);
		return new ResponseEntity<>(est, cabeceras, 238);
	}

	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Estudiante> actualizarParcial(@RequestBody Estudiante est, @PathVariable Integer id) {
		est.setId(id);
		Estudiante est2 = this.estudianteService.buscar(est.getId());
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mesaje_239", "Estudiante actualizado parcialmente");

		if (est.getNombre() != null) {
			est2.setNombre(est.getNombre());
		}

		if (est.getApellido() != null) {
			est2.setApellido(est.getApellido());
		}

		if (est.getFechaNacimiento() != null) {
			est2.setFechaNacimiento(est.getFechaNacimiento());
		}

		this.estudianteService.actualizar(est2);
		return new ResponseEntity<>(est2, cabeceras, 239);
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> borrar(@PathVariable Integer id) {
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_240", "La materia fue borrada");
		this.estudianteService.borrar(id);
		return new ResponseEntity<>("Estudiante borrado", cabeceras, 236);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Estudiante> buscarPorId(@PathVariable Integer id) {
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_236", "Corresponde a la consulta de un recurso");
		cabeceras.add("valor", "Estudiante encontrado");
		return new ResponseEntity<>(this.estudianteService.buscar(id), cabeceras, 236);
	}

	@GetMapping(path = "/buscar/{id}/nuevo/{dato}")
	public Estudiante buscar2(@PathVariable Integer id, @PathVariable String dato) {
		System.out.println("dato: " + dato);
		return this.estudianteService.buscar(id);
	}

	@GetMapping(path = "/mixto/{id}")
	public Estudiante buscarMixto(@PathVariable Integer id, @RequestParam String prueba) {
		System.out.println("Dato: " + id);
		System.out.println("Dato: " + prueba);
		return this.estudianteService.buscar(id);
	}

	@GetMapping(path = "/texto/plano")
	public String prueba() {
		String prueba = "Texto de prueba";
		return prueba;
	}

	// URL:http://localhost:8080/API/v1.0/Matricula/estudiantes/hateoas/1
	@GetMapping(path = "/hateoas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EstudianteTO buscarHateoas(@PathVariable Integer id) {
		EstudianteTO estu = this.estudianteService.buscarPorId(id);
		Link link = linkTo(methodOn(EstudianteController.class).buscarMateriaPorIdEstudiante(id))
				.withRel("susMaterias");
		Link link2 = linkTo(methodOn(EstudianteController.class).buscarPorId(id)).withSelfRel();
		estu.add(link);
		estu.add(link2);
		return estu;
	}

	// URL:http://localhost:8080/API/v1.0/Matricula/estudiantes/1/materias
	@GetMapping(path = "/{id}/materias", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MateriaTO> buscarMateriaPorIdEstudiante(@PathVariable Integer id) {
		return this.iMateriaService.buscarPorIdEstudiante(id);
	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/todos
	@GetMapping(path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EstudianteTO> buscarTodosHateoas() {

		List<EstudianteTO> estudiantesTO = this.estudianteService.buscarTodosEstudiantesTO();

		for (EstudianteTO estudianteTO : estudiantesTO) {
			Link link = linkTo(methodOn(EstudianteController.class).buscarMateriaPorIdEstudiante(estudianteTO.getId()))
					.withRel("susMaterias");
			estudianteTO.add(link);
		}
		return estudiantesTO;
	}

	// URL:http://localhost:8080/API/v1.0/Matricula/estudiantes/1726897200/borrarPorCedula
	@DeleteMapping(path = "/{cedula}/borrarPorCedula", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> borrarPorCedula(@PathVariable String cedula) {
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_236", "Corresponde a la consulta de un recurso");
		this.estudianteService.eliminarPorCedula(cedula);
		return new ResponseEntity<>("Borrar", cabeceras, HttpStatus.OK);

	}

	// URL:http://localhost:8080/API/v1.0/Matricula/estudiantes/3

	@GetMapping(path = "/{cedula}/buscarPorCedula", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstudianteTO> buscarPorCedula(@PathVariable String cedula) {
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_236", "Corresponde a la consulta de un recurso");
		EstudianteTO estudianteTO = this.estudianteService.buscarPorCedula(cedula);
		return new ResponseEntity<>(estudianteTO, cabeceras, HttpStatus.OK);

	}

	// URL:http://localhost:8080/API/v1.0/Matricula/estudiantes/1726897299/buscarPorCedula
	@PutMapping(path = "/{cedula}/buscarPorCedula", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstudianteTO> actualizarPorCedula(@RequestBody EstudianteTO estudianteTO,
			@PathVariable String cedula) {
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_236", "actualizarPorCedula");
		EstudianteTO estudianteTO2 = this.estudianteService.buscarPorCedula(cedula);
		estudianteTO.setId(estudianteTO2.getId());

		this.estudianteService.actualizarPorCedula(estudianteTO);
		return new ResponseEntity<>(estudianteTO2, cabeceras, HttpStatus.OK);

	}
}
