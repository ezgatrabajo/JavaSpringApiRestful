package com.elementary.spring.mvc.model;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Paises")
public class Pais {

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


	public Pais(int id, String nombre, String sigla) {
		this.id = id;
		this.nombre = nombre;
		this.sigla = sigla;
	}

	public Pais() {
	}

	@Override
	public String toString() {
		return "Pais{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", sigla='" + sigla + '\'' +
				'}';
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
