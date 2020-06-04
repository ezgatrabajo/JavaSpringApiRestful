package com.elementary.spring.mvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Ciudades")
public class Ciudad {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "nombre", nullable = false, length = 100)
	@Size(min = 3, message = "Debe tener al menos 3 caracteres")
	@NotNull
	private String nombre;

	@Column(name = "sigla", nullable = true, length = 4)
	@Size(max = 4, message = "Debe tener maximo 4 caracteres")
	private String sigla;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Provincia provincia;

	public Ciudad(int id, String nombre, String sigla, Provincia provincia) {
		this.id = id;
		this.nombre = nombre;
		this.sigla = sigla;
		this.provincia = provincia;
	}

	public Ciudad() {
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


	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
}
