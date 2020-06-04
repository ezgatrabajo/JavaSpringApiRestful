package com.elementary.spring.mvc.model;

import com.elementary.spring.mvc.core.enums.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Perfiles")
public class Perfil {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="id")
    private int id;

	@OneToOne()
	private Usuario user;


	@Column(name="nombre", length = 50)
	@Size(min=3, message="Debe tener al menos 3 caracteres")
	@NotNull
	private String nombre;


	@Column(name="apellido", length = 50)
	@Size(min=3, message="Debe tener al menos 3 caracteres")
	@NotNull
	private String apellido;


	@Column(name="telefono", length = 50)
	@Size(min=6, message="Debe tener al menos 6 caracteres")
	private String telefono;

	@Column(name="nrodocumento", length = 25)
	private Integer nrodocumento;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Tipodocumento tipodocumento;


	@Column(name="web", length = 150)
	private String web;


	@Column(name="cbu", length = 25)
	@Size(min=22, max=22,  message="Debe ser de 22 caracteres")
	private String cbu;

	@Column(name="matricula", length = 25)
	private String matricula;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Profesion profesion;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Especialidad especialidad;

	@Column(name="genero", length = 1)
	private Genero genero;


	public Perfil() {
	}


}
