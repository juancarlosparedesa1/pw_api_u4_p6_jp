package com.edu.uce.pw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.uce.pw.repository.IEstudianteRepository;
import com.edu.uce.pw.repository.modelo.Estudiante;
import com.edu.uce.pw.service.to.EstudianteTO;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

	@Autowired
	private IEstudianteRepository estudianteRepository;

	@Override
	public void ingresar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRepository.insertar(estudiante);
	}

	@Override
	public Estudiante buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.estudianteRepository.seleccionar(id);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRepository.actualizar(estudiante);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.estudianteRepository.eliminar(id);
	}

	// convertidor recibe un Estudiante y retorna un EstudainteTO
	public EstudianteTO convertir(Estudiante estu) {
		EstudianteTO estTo = new EstudianteTO();
		estTo.setId(estu.getId());
		estTo.setNombre(estu.getNombre());
		estTo.setApellido(estu.getApellido());
		estTo.setFechaNacimiento(estu.getFechaNacimiento());
		return estTo;
	}

	@Override
	public EstudianteTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		Estudiante est = this.estudianteRepository.seleccionar(id);
		return this.convertir(est);
	}

	@Override
	public List<EstudianteTO> buscarTodosEstudiantesTO() {
		// TODO Auto-generated method stub
		List<Estudiante> estudiantes = this.estudianteRepository.seleccionarTodosEstudiantes();
		List<EstudianteTO> estudiantesTO = new ArrayList<>();
		for (Estudiante estudiante : estudiantes) {
			EstudianteTO estudianteTO = this.convertir(estudiante);
			estudiantesTO.add(estudianteTO);
		}
		return estudiantesTO;
	}

	private Estudiante ToAEstudiante(EstudianteTO estudianteTO) {

		Estudiante estudiante = new Estudiante();
		estudiante.setApellido(estudianteTO.getApellido());
		estudiante.setCedula(estudianteTO.getCedula());
		estudiante.setFechaNacimiento(estudiante.getFechaNacimiento());
		estudiante.setGenero(estudianteTO.getGenero());
		estudiante.setId(estudianteTO.getId());
		estudiante.setNombre(estudianteTO.getNombre());

		return estudiante;

	}

	@Override
	public EstudianteTO buscarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		return this.convertir(this.estudianteRepository.seleccionarPorCedula(cedula));
	}

	@Override
	public void actualizarPorCedula(EstudianteTO estudianteTO) {
		// TODO Auto-generated method stub
		Estudiante estudiante = this.ToAEstudiante(estudianteTO);
	}

	@Override
	public void eliminarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		// manejamos excepciones si no encuentra el estudiante
		try {

			this.estudianteRepository.eliminarPorCedula(cedula);
		} catch (Exception e) {
			// TODO: handle exception

			System.err.println("Error en la eliminarcion" + e);
		}

	}
}
