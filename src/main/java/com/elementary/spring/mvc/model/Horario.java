package com.elementary.spring.mvc.model;

import com.elementary.spring.mvc.core.enums.Dias;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="Horarios")
public class Horario extends RepresentationModel<Horario> {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private int id;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Usuario profesional;


	@Column(name = "dia", length = 1)
	@NotNull
	private Dias dia;


	@Column(name = "horadesde")
	@NotNull
	@Temporal(TemporalType.TIME)
	private java.util.Date horadesde;


	@Column(name = "horahasta")
	@NotNull
	@Temporal(TemporalType.TIME)
	private java.util.Date horahasta;


	@Column(name = "notas", length = 255, nullable = true)
	private String notas;


	public Horario(){}


	@Override
	public String toString() {
		return "Horario{" +
				"id=" + id +
				", profesional=" + profesional +
				", dia=" + dia +
				", horadesde=" + horadesde +
				", horahasta=" + horahasta +
				", notas='" + notas + '\'' +
				'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getProfesional() {
		return profesional;
	}

	public void setProfesional(Usuario profesional) {
		this.profesional = profesional;
	}

	public Dias getDia() {
		return dia;
	}

	public void setDia(Dias dia) {
		this.dia = dia;
	}

	public Date getHoradesde() {
		return horadesde;
	}

	public void setHoradesde(Date horadesde) {
		this.horadesde = horadesde;
	}

	public Date getHorahasta() {
		return horahasta;
	}

	public void setHorahasta(Date horahasta) {
		this.horahasta = horahasta;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}


}
