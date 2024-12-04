package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

public class DetallesVentas {

	private int codigo;
	private Ventas cabeceraVentas;
	private Producto producto;
	private int cantidad;
	private BigDecimal precioVenta;
	private BigDecimal subtotal;
	private BigDecimal subtotalIva;

	public DetallesVentas() {
	}

	public DetallesVentas(int codigo, Ventas cabeceraVentas, Producto producto, int cantidad, BigDecimal precioVenta,
			BigDecimal money, BigDecimal subtotalIva) {
		super();
		this.codigo = codigo;
		this.cabeceraVentas = cabeceraVentas;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioVenta = precioVenta;
		this.subtotal = money;
		this.subtotalIva = subtotalIva;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Ventas getCabeceraVentas() {
		return cabeceraVentas;
	}

	public void setCabeceraVentas(Ventas cabeceraVentas) {
		this.cabeceraVentas = cabeceraVentas;
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

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void getSubtotal(BigDecimal money) {
		this.subtotal = money;
	}

	public BigDecimal getSubtotalIva() {
		return subtotalIva;
	}

	public void setSubtotalIva(BigDecimal subtotalIva) {
		this.subtotalIva = subtotalIva;
	}

	@Override
	public String toString() {
		return "DetallesVentas [codigo=" + codigo + ", cabeceraVentas=" + cabeceraVentas + ", producto=" + producto
				+ ", cantidad=" + cantidad + ", precioVenta=" + precioVenta + ", money=" + subtotal + ", subtotalIva="
				+ subtotalIva + "]";
	}

}
