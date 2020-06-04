package com.elementary.spring.mvc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="pedidos")
public class Pedido {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="id")
    private int id;
	
	private Date fechacreated;
	
	private Date fechaupdated;
	
	@ManyToOne
	private Usuario usuario;
	
	private double descuento;
	
	private double impuestos;
	
	private double subtotal;
	
	@OneToMany
    private List<PedidoItem> items = new ArrayList<PedidoItem>();
	
	private double total;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechacreated() {
		return fechacreated;
	}

	public void setFechacreated(Date fechacreated) {
		this.fechacreated = fechacreated;
	}

	public Date getFechaupdated() {
		return fechaupdated;
	}

	public void setFechaupdated(Date fechaupdated) {
		this.fechaupdated = fechaupdated;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(double impuestos) {
		this.impuestos = impuestos;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public List<PedidoItem> getItems() {
		return items;
	}

	public void setItems(List<PedidoItem> items) {
		this.items = items;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Pedido(int id, Date fechacreated, Date fechaupdated, Usuario usuario, double descuento, double impuestos,
			double subtotal, List<PedidoItem> items, double total) {
		super();
		this.id = id;
		this.fechacreated = fechacreated;
		this.fechaupdated = fechaupdated;
		this.usuario = usuario;
		this.descuento = descuento;
		this.impuestos = impuestos;
		this.subtotal = subtotal;
		this.items = items;
		this.total = total;
	}

	public Pedido() {
		super();
	}
	
	
}
