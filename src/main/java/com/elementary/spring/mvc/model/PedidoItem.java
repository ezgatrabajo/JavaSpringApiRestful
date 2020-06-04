package com.elementary.spring.mvc.model;

import javax.persistence.*;

@Entity
@Table(name="pedidoitems")
public class PedidoItem {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="id")
    private int id;
	
	@ManyToOne
	private Pedido pedido;
	
	@ManyToOne
	private Producto producto;
	
	private double cantidad;
	
	private double descuento;
	
	private double subtotal;
	
	private double total;

	public PedidoItem(Pedido pedido, Producto producto, double cantidad, double descuento, double subtotal,
			double total) {
		super();
		this.pedido = pedido;
		this.producto = producto;
		this.cantidad = cantidad;
		this.descuento = descuento;
		this.subtotal = subtotal;
		this.total = total;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public PedidoItem() {
		super();
	}

	@Override
	public String toString() {
		return "PedidoItem [pedido=" + pedido + ", producto=" + producto + ", cantidad=" + cantidad + ", descuento="
				+ descuento + ", subtotal=" + subtotal + ", total=" + total + "]";
	}
	
	
	
}
