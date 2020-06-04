package com.elementary.spring.mvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Tipodocumentos")
public class Tipodocumento {

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


	@Column(name="sigla", length = 4)
	@Size(max=4, message="Debe tener maximo 4 caracteres")
	private String sigla;



	public Tipodocumento() {
	}

	public Tipodocumento(int id,  String nombre, String descripicion,  String sigla) {
		this.id = id;
		this.nombre = nombre;
		this.descripicion = descripicion;
		this.sigla = sigla;
	}

	@Override
	public String toString() {
		return "Tipodocumento{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", descripicion='" + descripicion + '\'' +
				", sigla='" + sigla + '\'' +
				'}';
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
