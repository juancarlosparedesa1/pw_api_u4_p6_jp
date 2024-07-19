package com.edu.uce.pw.service;

import java.util.List;

import com.edu.uce.pw.repository.modelo.Estudiante;
import com.edu.uce.pw.service.to.EstudianteTO;

public interface IEstudianteService {
	public void ingresar(Estudiante estudiante);

	public EstudianteTO buscarPorId(Integer id);

	public Estudiante buscar(Integer id);

	public void actualizar(Estudiante estudiante);

	public void borrar(Integer id);

	List<EstudianteTO> buscarTodosEstudiantesTO();

	// taller33
	EstudianteTO buscarPorCedula(String cedula);

	public void actualizarPorCedula(EstudianteTO estudianteTO);

	public void eliminarPorCedula(String cedula);
}
