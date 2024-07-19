package com.edu.uce.pw.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edu.uce.pw.repository.modelo.Materia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class MateriaRepositoryImpl implements IMateriaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Materia seleccionar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Materia.class, id);
	}

	@Override
	public void actualizar(Materia materia) {
		// TODO Auto-generated method stub
		this.entityManager.merge(materia);

	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.seleccionar(id));
	}

	@Override
	public void insertar(Materia materia) {
		// TODO Auto-generated method stub
		this.entityManager.persist(materia);
	}

	@Override
	public List<Materia> SeleccionarPorNumeroCreditos(String numeroCreditos) {
		// TODO Auto-generated method stub
		TypedQuery<Materia> myQuery = this.entityManager
				.createQuery("SELECT m FROM Materia m WHERE e.numeroCreditos=:numeroCreditos", Materia.class);
		myQuery.setParameter("numeroCreditos", numeroCreditos);

		return myQuery.getResultList();
	}

	@Override
	public List<Materia> SeleccionarPorNumeroCreditos(Integer numeroCreditos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Materia> seleccionarPorIdEstudiante(Integer id) {
		TypedQuery<Materia> myQuery = this.entityManager
				.createQuery("SELECT m FROM Materia m WHERE m.estudiante.id=:id", Materia.class);
		myQuery.setParameter("id", id);
		return myQuery.getResultList();
	}

}