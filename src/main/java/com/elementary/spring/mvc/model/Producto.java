package com.elementary.spring.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Producto")
public class Producto {
	@GeneratedValue
	@Id
	@Column(name="id")
    private int id;
	
	private String nombre;
	
	private String descripcion;

	private double precio;
	
	@ManyToOne
	private Categoria categoria;
	
	@ManyToOne
	private Marca marca;
	
	
	private boolean isOferta;
	
	
	private double precioOferta;
	
	
	
	public Producto() {
		super();
	}

	public Producto(int id, String nombre, String descripcion, double precio, Categoria categoria, Marca marca,
			boolean isOferta, double precioOferta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.categoria = categoria;
		this.marca = marca;
		this.isOferta = isOferta;
		this.precioOferta = precioOferta;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", categoria=" + categoria + ", marca=" + marca + ", isOferta=" + isOferta + ", precioOferta="
				+ precioOferta + "]";
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public boolean isOferta() {
		return isOferta;
	}

	public void setOferta(boolean isOferta) {
		this.isOferta = isOferta;
	}

	public double getPrecioOferta() {
		return precioOferta;
	}

	public void setPrecioOferta(double precioOferta) {
		this.precioOferta = precioOferta;
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

	
	
}
