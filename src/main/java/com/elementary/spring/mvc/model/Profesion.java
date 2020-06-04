package com.elementary.spring.mvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Profesiones")
public class Profesion {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="id")
    private int id;

	@Column(name="nombre", length = 100)
	@Size(min=3, message="Debe tener al menos 3 caracteres")
	@NotNull
	private String nombre;


	@Column(name="descripcion", length = 255)
	private String descripicion;


	public Profesion() {
	}

	public Profesion(int id, String nombre, String descripicion) {
		this.id = id;
		this.nombre = nombre;
		this.descripicion = descripicion;
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

	public String getDescripicion() {
		return descripicion;
	}

	public void setDescripicion(String descripicion) {
		this.descripicion = descripicion;
	}

	@Override
	public String toString() {
		return "Profesion{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", descripicion='" + descripicion + '\'' +
				'}';
	}
}
