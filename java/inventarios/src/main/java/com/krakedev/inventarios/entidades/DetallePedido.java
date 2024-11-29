package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

public class DetallePedido {
	private int codigo;
	private Pedido cabeceraPedido;
	private Producto producto;
	private int cantidad;
	private BigDecimal subtotal;
	private int catidadRecibida;

	public DetallePedido() {
	}

	public DetallePedido(int codigo, Pedido cabeceraPedido, Producto producto, int cantidad, BigDecimal subtotal,
			int catidadRecibida) {
		this.codigo = codigo;
		this.cabeceraPedido = cabeceraPedido;
		this.producto = producto;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.catidadRecibida = catidadRecibida;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Pedido getCabeceraPedido() {
		return cabeceraPedido;
	}

	public void setCabeceraPedido(Pedido cabeceraPedido) {
		this.cabeceraPedido = cabeceraPedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public int getCatidadRecibida() {
		return catidadRecibida;
	}

	public void setCatidadRecibida(int catidadRecibida) {
		this.catidadRecibida = catidadRecibida;
	}

	@Override
	public String toString() {
		return "DetallePedido [codigo=" + codigo + ", cabeceraPedido=" + cabeceraPedido + ", producto=" + producto
				+ ", cantidad=" + cantidad + ", subtotal=" + subtotal + ", catidadRecibida=" + catidadRecibida + "]";
	}
	
	

}
