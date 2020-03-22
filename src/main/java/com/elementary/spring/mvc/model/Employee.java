package com.elementary.spring.mvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Table(name="Employee")
@Entity()
public class Employee implements Serializable{
	   
	@GeneratedValue
	@Id
	@Column(name="id")
    private int id;
    
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
    private String apellido; 
	
	@Column(name="salary")
    private int salary;  
	
	
	
	
	
	
	public Employee() {
		super();
	}

	public Employee(int id, String nombre, String apellido, int salary) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", salary=" + salary
				+ "]";
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
}
