package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.Pedido;
import com.krakedev.inventarios.excepciones.KrakedevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class PedidosBDD {

	public void insertar(Pedido pedido) throws KrakedevException {
		Connection con = null;
		PreparedStatement psDet = null;
		PreparedStatement ps = null;
		ResultSet rsClave=null;
		int codigoCabecera = 0;

		Date fechaActual = new Date();
		java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());
		
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("insert into cabecera_pedido(proveedor,fecha,estado) "
					+ "values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, pedido.getProveedor().getIdentificador());
			ps.setDate(2, fechaSQL);
			ps.setString(3, "S");
		
			ps.executeUpdate();
			
			rsClave = ps.getGeneratedKeys();
			
			if(rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}
			
			ArrayList<DetallePedido> detallesPedido = pedido.getDetalles();	
			DetallePedido det;
			for(int i = 0; i<detallesPedido.size();i++) {
				det = detallesPedido.get(i);
				psDet = con.prepareStatement("insert into detalle_pedido(cabecera_pedido,producto,cantidad,subtotal,cantidad_recibida) "
						+ "values(?,?,?,?,?);");
				psDet.setInt(1, codigoCabecera);
				psDet.setInt(2, det.getProducto().getCodigo());
				psDet.setInt(3, det.getCantidad());
				BigDecimal pv = det.getProducto().getPrecioVenta();
				BigDecimal catidad = new BigDecimal(det.getCantidad());
				psDet.setBigDecimal(4, pv.multiply(catidad));
				psDet.setInt(5, 0);
				
				psDet.executeUpdate();
				
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}
	}
}
