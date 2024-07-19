package com.edu.uce.pw.service.to;

import java.io.Serializable;

public class MateriaTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6202320458450911624L;

	private Integer id;

	private String nombre;

	private String codigo;

	private Integer numeroCreditos;

	// SET Y GET

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getNumeroCreditos() {
		return numeroCreditos;
	}

	public void setNumeroCreditos(Integer numeroCreditos) {
		this.numeroCreditos = numeroCreditos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
