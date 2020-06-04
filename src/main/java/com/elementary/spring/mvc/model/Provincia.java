package com.elementary.spring.mvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Provincias")
public class Provincia {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="id")
    private int id;


	@Column(name="nombre", nullable = false, length = 100)
	@Size(min=3, message="Debe tener al menos 3 caracteres")
	@NotNull
	private String nombre;


	@Column(name="sigla", nullable = true, length = 4)
	@Size(max=4, message="Debe tener maximo 4 caracteres")
	private String sigla;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pais pais;

	public Provincia(int id, String nombre, String sigla, Pais pais) {
		this.id     = id;
		this.nombre = nombre;
		this.sigla  = sigla;
		this.pais   = pais;
	}

	public Provincia() {
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
}
