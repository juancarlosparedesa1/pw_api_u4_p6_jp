package com.edu.uce.pw.repository;

import java.util.List;

import com.edu.uce.pw.repository.modelo.Materia;

public interface IMateriaRepository {

	// CRUD
	Materia seleccionar(Integer id);

	void actualizar(Materia materia);

	void eliminar(Integer id);

	void insertar(Materia materia);

	List<Materia> SeleccionarPorNumeroCreditos(Integer numeroCreditos);

	List<Materia> SeleccionarPorNumeroCreditos(String numeroCreditos);

	public List<Materia> seleccionarPorIdEstudiante(Integer id);
}
