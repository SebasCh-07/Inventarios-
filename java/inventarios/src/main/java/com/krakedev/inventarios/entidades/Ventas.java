package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Ventas {

	private int codigo;
	private Date fecha;
	private BigDecimal totalIva;
	private BigDecimal iva;
	private BigDecimal total;
	
	private ArrayList<DetallesVentas> detalles;

	public Ventas() {
	}

	public Ventas(int codigo, Date fecha, BigDecimal totalIva, BigDecimal iva, BigDecimal total) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.totalIva = totalIva;
		this.iva = iva;
		this.total = total;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getTotalIva() {
		return totalIva;
	}

	public void setTotalIva(BigDecimal totalIva) {
		this.totalIva = totalIva;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	

	public ArrayList<DetallesVentas> getDetalles() {
		return detalles;
	}

	public void setDetalles(ArrayList<DetallesVentas> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "Ventas [codigo=" + codigo + ", fecha=" + fecha + ", totalIva=" + totalIva + ", iva=" + iva + ", total="
				+ total + "]";
	}

}
