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

import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.EstadoPedido;
import com.krakedev.inventarios.entidades.Pedido;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.entidades.UnidadDeMedida;
import com.krakedev.inventarios.excepciones.KrakedevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class PedidosBDD {

	public void insertar(Pedido pedido) throws KrakedevException {
		Connection con = null;
		PreparedStatement psDet = null;
		PreparedStatement ps = null;
		ResultSet rsClave = null;
		int codigoCabecera = 0;

		Date fechaActual = new Date();
		java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("insert into cabecera_pedido(proveedor,fecha,estado) " + "values(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, pedido.getProveedor().getIdentificador());
			ps.setDate(2, fechaSQL);
			ps.setString(3, "S");

			ps.executeUpdate();

			rsClave = ps.getGeneratedKeys();

			if (rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}

			ArrayList<DetallePedido> detallesPedido = pedido.getDetalles();
			DetallePedido det;
			for (int i = 0; i < detallesPedido.size(); i++) {
				det = detallesPedido.get(i);
				psDet = con.prepareStatement(
						"insert into detalle_pedido(cabecera_pedido,producto,cantidad,subtotal,cantidad_recibida) "
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

	public void actualizar(Pedido pedido) throws KrakedevException {
		Connection con = null;
		PreparedStatement psDet = null;
		PreparedStatement ps = null;
		PreparedStatement psHist = null;

		Date fechaActual = new Date();
		Timestamp fechaHoraActual = new Timestamp(fechaActual.getTime());

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("update cabecera_pedido set  estado=? where numero=?");
			ps.setString(1, "R");
			ps.setInt(2, pedido.getNumero());

			ps.executeUpdate();

			ArrayList<DetallePedido> detallesPedido = pedido.getDetalles();
			DetallePedido det;
			for (int i = 0; i < detallesPedido.size(); i++) {
				det = detallesPedido.get(i);
				psDet = con
						.prepareStatement("update detalle_pedido set cantidad_recibida=?, subtotal=? where codigo=?");
				BigDecimal pv = det.getProducto().getPrecioVenta();
				BigDecimal catidadR = new BigDecimal(det.getCatidadRecibida());
				psDet.setInt(1, det.getCatidadRecibida());
				psDet.setBigDecimal(2, pv.multiply(catidadR));
				psDet.setInt(3, det.getCodigo());

				psDet.executeUpdate();

				psHist = con.prepareStatement(
						"insert into historial_stock(fecha,referencia,producto,cantidad) " + "values(?,?,?,?)");
				psHist.setTimestamp(1, fechaHoraActual);
				psHist.setString(2, "Pedido " + pedido.getNumero());
				psHist.setInt(3, det.getProducto().getCodigo());
				psHist.setInt(4, det.getCatidadRecibida());
				psHist.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}
	}
	
	
	public ArrayList<Pedido> buscar(String identifcadorProve) throws KrakedevException {
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		ArrayList<DetallePedido> detalles = new ArrayList<DetallePedido>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pedido pedido = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(
					"select cp.numero, prov.identificador, td.codigo as codigo_TD, prov.nombre as nombre_prove, prov.telefono, prov.correo, prov.direccion, cp.fecha, ep.codigo as codigo_estado, ep.descripcion as descripcion_EP, "
					+ "dp.codigo as codigo_DP, prod.codigo_pro, prod.nombre as nombre_prod, udm.nombre as nombreUDM, prod.precio_venta, prod.tiene_iva, prod.coste, cat.codigo_cat, prod.stock, dp.cantidad, dp.subtotal, dp.cantidad_recibida "
					+ "from cabecera_pedido cp, detalle_pedido dp, proveedores prov, tipo_documento td, estados_pedido ep, productos prod, unidades_medida udm, categorias cat "
					+ "where cp.proveedor = ? and cp.numero = dp.cabecera_pedido and cp.proveedor = prov.identificador and td.codigo = prov.tipo_documento "
					+ "and cp.estado = ep.codigo and prod.codigo_pro = dp.producto and prod.udm = udm.nombre and cat.codigo_cat = prod.categoria");
			ps.setString(1, identifcadorProve);
			rs = ps.executeQuery();

			while (rs.next()) {

				int numeroCP = rs.getInt("numero");
				Date fecha = rs.getDate("fecha");
				String CodigoEstado = rs.getString("codigo_estado");
				String DescripcionEstado = rs.getString("descripcion_ep");
				
				String identificador = rs.getString("identificador");
				String codigoTipoDucu = rs.getString("codigo_td");
				String nombreProveedor = rs.getString("nombre_prove");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				
				
				int codigoDetallePedido = rs.getInt("codigo_dp");
				int cantidad = rs.getInt("cantidad");
				String subtotalStr = rs.getString("subtotal"); 
				BigDecimal subtotal = new BigDecimal(subtotalStr.replace("$", "").replace(",", "").trim());
				int cantidadRecibida = rs.getInt("cantidad_recibida");
				
				int codigoProdu = rs.getInt("codigo_pro");
				String nombreProdu = rs.getString("nombre_prod");
				String NombreUDM = rs.getString("nombreudm");
				String precioVentaStr = rs.getString("precio_venta");
				BigDecimal precioVenta = new BigDecimal(precioVentaStr.replace("$", "").replace(",", "").trim());
				boolean tieneIva = rs.getBoolean("tiene_iva");
				
				String CosteStr = rs.getString("Coste");
				BigDecimal Coste = new BigDecimal(CosteStr.replace("$", "").replace(",", "").trim());
				int codigoCategoria = rs.getInt("codigo_cat");
				int stock = rs.getInt("stock");
				
				EstadoPedido EP = new EstadoPedido();
				EP.setCodigo(CodigoEstado);
				EP.setDescripcion(DescripcionEstado);
			
				TipoDocumento TD = new TipoDocumento();
				TD.setCodigo(codigoTipoDucu);
				
				Proveedor prov = new Proveedor();
				prov.setIdentificador(identificador);
				prov.setTipoDocumento(TD);
				prov.setNombre(nombreProveedor);
				prov.setTelefono(telefono);
				prov.setCorreo(correo);
				prov.setDireccion(direccion);
				
				Pedido pedi = new Pedido();
				pedi.setNumero(numeroCP);
				
				UnidadDeMedida UDM = new UnidadDeMedida();
				UDM.setNombre(NombreUDM);
				
				Categoria cat = new Categoria();
				cat.setCodigo(codigoCategoria);
				
				Producto prod = new Producto();
				prod.setCodigo(codigoProdu);
				prod.setNombre(nombreProdu);
				prod.setUdm(UDM);
				prod.setPrecioVenta(precioVenta);
				prod.setTieneIva(tieneIva);
				prod.setCoste(Coste);
				prod.setCategoria(cat);
				prod.setStock(stock);
				
				DetallePedido DP = new DetallePedido();
				DP.setCodigo(codigoDetallePedido);
				DP.setCabeceraPedido(pedi);
				DP.setProducto(prod);
				DP.setCantidad(cantidad);
				DP.setSubtotal(subtotal);
				DP.setCatidadRecibida(cantidadRecibida);
				
				detalles.add(DP);
				
				pedido = new Pedido();
				pedido.setNumero(numeroCP);
				pedido.setProveedor(prov);
				pedido.setFecha(fecha);
				pedido.setEstado(EP);
				pedido.setDetalles(detalles);
				
				pedidos.add(pedido);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}

		return pedidos;
	}



}
