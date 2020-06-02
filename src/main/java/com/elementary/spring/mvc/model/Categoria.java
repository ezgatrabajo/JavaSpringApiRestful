package com.elementary.spring.mvc.model;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Categoria")
public class Categoria extends RepresentationModel<Customer> {
	@GeneratedValue
	@Id
	@Column(name="id")
    private int id;
	
	@Column(name="nombre")
	@Size(min=3, message="Debe tener al menos 3 caracteres")
	@NotNull
	private String nombre;
	
	@Column(name="descripcion")
	@Size(min=10, message="Debe tener al menos 3 caracteres")
	@NotNull
	private String descripcion;

	public Categoria(int id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Categoria() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
}
