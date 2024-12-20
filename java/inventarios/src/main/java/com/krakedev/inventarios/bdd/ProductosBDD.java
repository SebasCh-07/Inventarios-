package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.UnidadDeMedida;
import com.krakedev.inventarios.excepciones.KrakedevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProductosBDD {

	public ArrayList<Producto> buscar(String subcadena) throws KrakedevException {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Producto produ = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(
					"select prod.codigo_pro, prod.nombre as nombre_prod, udm.nombre as nombre_udm, udm.descripcion as descripcion_udm, "
							+ "cast(prod.precio_venta as decimal(6,2)), prod .tiene_iva, cast(prod.coste as decimal(5,2)), prod.categoria, cat.codigo_cat, cat.nombre as nombre_categoria, stock "
							+ "from productos prod, unidades_medida udm, categorias cat "
							+ "where prod.udm = udm.nombre and prod.categoria = cat.codigo_cat and prod.nombre ilike ?");
			ps.setString(1, "%" + subcadena + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				int codigoProd = rs.getInt("codigo_pro");
				String nombre = rs.getString("nombre_prod");
				String nombreUDM = rs.getString("nombre_udm");
				String descripcionUDM = rs.getString("descripcion_udm");
				BigDecimal precioVenta = rs.getBigDecimal("precio_venta");
				boolean tieneIva = rs.getBoolean("tiene_iva");
				BigDecimal coste = rs.getBigDecimal("coste");
				int codigoCategoria = rs.getInt("codigo_cat");
				String nombreCategoria = rs.getString("nombre_categoria");
				int stock = rs.getInt("stock");

				UnidadDeMedida udm = new UnidadDeMedida();
				udm.setNombre(nombreUDM);
				udm.setDescripcion(descripcionUDM);

				Categoria cat = new Categoria();
				cat.setCodigo(codigoCategoria);
				cat.setNombre(nombreCategoria);

				produ = new Producto();
				produ.setCodigo(codigoProd);
				produ.setNombre(nombre);
				produ.setUdm(udm);
				produ.setPrecioVenta(precioVenta);
				produ.setTieneIva(tieneIva);
				produ.setCoste(coste);
				produ.setCategoria(cat);
				produ.setStock(stock);

				productos.add(produ);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}

		return productos;
	}

	public void insertar(Producto produ) throws KrakedevException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("insert into productos(nombre,udm,precio_venta,tiene_iva,coste,categoria,stock) "
					+ "values (?,?,?,?,?,?,?)");
			ps.setString(1, produ.getNombre());
			ps.setString(2, produ.getUdm().getNombre());
			ps.setBigDecimal(3, produ.getPrecioVenta());
			ps.setBoolean(4, produ.isTieneIva());
			ps.setBigDecimal(5, produ.getCoste());
			ps.setInt(6, produ.getCategoria().getCodigo());
			ps.setInt(7, produ.getStock());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}

	}

	public void actualizar(Producto produ) throws KrakedevException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("update productos set nombre=?, udm=?, precio_venta=?, tiene_iva=?, coste=?, categoria=?, stock=?  where codigo_pro=?");
			ps.setString(1, produ.getNombre());
			ps.setString(2, produ.getUdm().getNombre());
			ps.setBigDecimal(3, produ.getPrecioVenta());
			ps.setBoolean(4, produ.isTieneIva());
			ps.setBigDecimal(5, produ.getCoste());
			ps.setInt(6, produ.getCategoria().getCodigo());
			ps.setInt(7, produ.getStock());
			ps.setInt(8, produ.getCodigo());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}
	}
	
	public Producto buscarProducto(int codigo) throws KrakedevException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Producto produ = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(
					"select prod.codigo_pro, prod.nombre as nombre_prod, udm.nombre as nombre_udm, udm.descripcion as descripcion_udm, "
							+ "cast(prod.precio_venta as decimal(6,2)), prod .tiene_iva, cast(prod.coste as decimal(5,2)), prod.categoria, cat.codigo_cat, cat.nombre as nombre_categoria, stock "
							+ "from productos prod, unidades_medida udm, categorias cat "
							+ "where prod.udm = udm.nombre and prod.categoria = cat.codigo_cat and prod.codigo_pro = ?");
			ps.setInt(1, codigo);
			rs = ps.executeQuery();

			while (rs.next()) {
				int codigoProd = rs.getInt("codigo_pro");
				String nombre = rs.getString("nombre_prod");
				String nombreUDM = rs.getString("nombre_udm");
				String descripcionUDM = rs.getString("descripcion_udm");
				BigDecimal precioVenta = rs.getBigDecimal("precio_venta");
				boolean tieneIva = rs.getBoolean("tiene_iva");
				BigDecimal coste = rs.getBigDecimal("coste");
				int codigoCategoria = rs.getInt("codigo_cat");
				String nombreCategoria = rs.getString("nombre_categoria");
				int stock = rs.getInt("stock");

				UnidadDeMedida udm = new UnidadDeMedida();
				udm.setNombre(nombreUDM);
				udm.setDescripcion(descripcionUDM);

				Categoria cat = new Categoria();
				cat.setCodigo(codigoCategoria);
				cat.setNombre(nombreCategoria);

				produ = new Producto();
				produ.setCodigo(codigoProd);
				produ.setNombre(nombre);
				produ.setUdm(udm);
				produ.setPrecioVenta(precioVenta);
				produ.setTieneIva(tieneIva);
				produ.setCoste(coste);
				produ.setCategoria(cat);
				produ.setStock(stock);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}

		return produ;
	}

}
