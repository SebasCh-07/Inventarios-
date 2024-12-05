package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.excepciones.KrakedevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class CategoriasBDD {
	public void insertar(Categoria cat) throws KrakedevException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("insert into categorias(nombre,categoria_padre) "
					+ "values (?,?)");
			ps.setString(1, cat.getNombre());
			if (cat.getCategoriaPadre() != null) {
				ps.setInt(2, cat.getCategoriaPadre().getCodigo());
			}else {
				ps.setNull(2, java.sql.Types.INTEGER);
			}
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}

	}

	public void actualizar(Categoria cat) throws KrakedevException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement("update categorias set nombre=?, categoria_padre=? where codigo_cat=?");
			ps.setString(1, cat.getNombre());
			if (cat.getCategoriaPadre() != null) {
				ps.setInt(2, cat.getCategoriaPadre().getCodigo());
			}else {
				 ps.setNull(2, java.sql.Types.INTEGER);
			}
			ps.setInt(3, cat.getCodigo());
			

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}
	}
	
	public ArrayList<Categoria> buscar() throws KrakedevException {
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Categoria cat = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(
					"select * from categorias");
			rs = ps.executeQuery();

			while (rs.next()) {
				int codigo = rs.getInt("codigo_cat");
	            String nombre = rs.getString("nombre");
	            int codigoCategoriaPadre = rs.getInt("categoria_padre");
				
	            cat = new Categoria();
	            cat.setCodigo(codigo);
	            cat.setNombre(nombre);

	            if (codigoCategoriaPadre != 0) { 
	                Categoria categoriaPadre = obtenerCategoriaPorCodigo(codigoCategoriaPadre, con);
	                cat.setCategoriaPadre(categoriaPadre);
	            } else {
	                cat.setCategoriaPadre(null); 
	            }

	            categorias.add(cat);
	        }

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}

		return categorias;
	}
	
	private Categoria obtenerCategoriaPorCodigo(int codigo, Connection con) throws SQLException {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Categoria categoria = null;

	    try {
	        ps = con.prepareStatement("SELECT * FROM categorias WHERE codigo_cat = ?");
	        ps.setInt(1, codigo);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            categoria = new Categoria();
	            categoria.setCodigo(rs.getInt("codigo_cat"));
	            categoria.setNombre(rs.getString("nombre"));
	 
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}

	    return categoria;
	}
}
