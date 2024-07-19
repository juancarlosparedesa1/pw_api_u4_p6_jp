package com.edu.uce.pw.repository;

import java.util.List;

import com.edu.uce.pw.repository.modelo.Estudiante;

public interface IEstudianteRepository {
	// crud
	public void insertar(Estudiante estudiante);

	public Estudiante seleccionar(Integer id);

	public void actualizar(Estudiante estudiante);

	public void eliminar(Integer id);

	List<Estudiante> seleccionarTodosEstudiantes();

	// taller 33
	Estudiante seleccionarPorCedula(String cedula);

	public void eliminarPorCedula(String cedula);

}
