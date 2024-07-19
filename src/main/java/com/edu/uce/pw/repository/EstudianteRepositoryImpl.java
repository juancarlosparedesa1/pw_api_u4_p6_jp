package com.edu.uce.pw.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edu.uce.pw.repository.modelo.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EstudianteRepositoryImpl implements IEstudianteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.entityManager.persist(estudiante);
	}

	@Override
	public Estudiante seleccionar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Estudiante.class, id);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.entityManager.merge(estudiante);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.seleccionar(id));

	}

	@Override
	public List<Estudiante> seleccionarTodosEstudiantes() {
		TypedQuery<Estudiante> query = this.entityManager.createQuery("Select  e from Estudiante e ", Estudiante.class);

		return query.getResultList();
	}

	@Override
	public Estudiante seleccionarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		TypedQuery<Estudiante> query = this.entityManager
				.createQuery("Select e from Estudiante e Where e.cedula= :cedula", Estudiante.class);
		query.setParameter("cedula", cedula);

		return query.getSingleResult();
	}

	@Override
	public void eliminarPorCedula(String cedula) {
		TypedQuery<Estudiante> estudianteQuery = entityManager
				.createQuery("SELECT e FROM Estudiante e WHERE e.cedula = :cedula", Estudiante.class);
		estudianteQuery.setParameter("cedula", cedula);

		Estudiante estudiante;
		try {
			estudiante = estudianteQuery.getSingleResult();
		} catch (NoResultException e) {

			throw new EntityNotFoundException("No se encontró el estudiante con la cédula: " + cedula);
		}

		Query deleteMateriasQuery = entityManager
				.createQuery("DELETE FROM Materia m WHERE m.estudiante.id = :estudianteId");
		deleteMateriasQuery.setParameter("estudianteId", estudiante.getId());
		deleteMateriasQuery.executeUpdate();

		entityManager.remove(estudiante);
	}

}
