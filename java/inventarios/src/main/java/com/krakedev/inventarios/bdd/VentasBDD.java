package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios.entidades.DetallesVentas;
import com.krakedev.inventarios.entidades.Ventas;
import com.krakedev.inventarios.excepciones.KrakedevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class VentasBDD {

	public void insertar(Ventas ventas) throws KrakedevException {
		Connection con = null;
		PreparedStatement psCabecera = null;
		PreparedStatement psDetalle = null;
		PreparedStatement psActualizar = null;
		PreparedStatement psHistorial = null;
		ResultSet rsClave = null;

		BigDecimal totalNoIva = BigDecimal.ZERO;
		BigDecimal totalIva = BigDecimal.ZERO;
		BigDecimal IVA = new BigDecimal("0.12");

		Date fechaActual = new Date();
		Timestamp fechaHoraActual = new Timestamp(fechaActual.getTime());

		try {
			con = ConexionBDD.obtenerConexion();
			psCabecera = con.prepareStatement(
					"INSERT INTO cabecera_ventas(fecha, total_iva, iva, total) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			psCabecera.setTimestamp(1, fechaHoraActual);
			psCabecera.setBigDecimal(2, BigDecimal.ZERO);
			psCabecera.setBigDecimal(3, BigDecimal.ZERO);
			psCabecera.setBigDecimal(4, BigDecimal.ZERO);

			psCabecera.executeUpdate();
			rsClave = psCabecera.getGeneratedKeys();

			int codigoCabecera =0;
			if (rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}
			
			System.out.println("codigo Cabecera: "+codigoCabecera);
			
			ArrayList<DetallesVentas> detallesVentas = ventas.getDetalles();
			DetallesVentas det;
			for (int i = 0; i < detallesVentas.size(); i++) {
				det = detallesVentas.get(i);
				BigDecimal pv = det.getProducto().getPrecioVenta();
				BigDecimal cantidad = new BigDecimal(det.getCantidad());
				BigDecimal subtotal = pv.multiply(cantidad);

				psDetalle = con.prepareStatement(
						"INSERT INTO detalles_ventas(cabecera_ventas, producto, cantidad, precio_venta, subtotal, subtotal_iva) "
								+ "VALUES (?, ?, ?, ?, ?, ?)");
				psDetalle.setInt(1, codigoCabecera);
				psDetalle.setInt(2, det.getProducto().getCodigo());
				psDetalle.setInt(3, det.getCantidad());
				psDetalle.setBigDecimal(4, pv);
				psDetalle.setBigDecimal(5, subtotal);

				if (det.getProducto().isTieneIva() == true) {
					psDetalle.setBigDecimal(6, subtotal.add(subtotal.multiply(IVA)));
				} else {
					psDetalle.setBigDecimal(6, subtotal);
				}

				psDetalle.executeUpdate();

				if (det.getProducto().isTieneIva() == true) {
					totalIva = totalIva.add(subtotal.add(subtotal.multiply(IVA)));
				} else {
					totalNoIva = totalNoIva.add(subtotal);
				}

			}

			BigDecimal iva = totalIva.multiply(IVA);
			BigDecimal totalSinIva = totalIva.add(totalNoIva);
			BigDecimal total = totalSinIva.add(iva);

			psActualizar = con.prepareStatement("UPDATE cabecera_ventas SET total_iva = ?, iva = ?, total = ? WHERE codigo = ?");
			psActualizar.setBigDecimal(1, totalSinIva);
			psActualizar.setBigDecimal(2, iva);
			psActualizar.setBigDecimal(3, total);
			psActualizar.setInt(4, codigoCabecera);
			psActualizar.executeUpdate();

			
			for (int i = 0; i < detallesVentas.size(); i++) {
				det = detallesVentas.get(i);
				psHistorial = con.prepareStatement(
						"insert into historial_stock(fecha,referencia,producto,cantidad) " + "values(?,?,?,?)");
				psHistorial.setTimestamp(1, fechaHoraActual);
				psHistorial.setString(2, "VENTA " + codigoCabecera);
				psHistorial.setInt(3, det.getProducto().getCodigo());
				psHistorial.setInt(4, -det.getCantidad());
				psHistorial.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}
	}

}
