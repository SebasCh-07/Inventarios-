package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.excepciones.KrakedevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProveedoresBDD {
	public ArrayList<Proveedor> buscar(String subcadena) throws KrakedevException{
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		Connection con = null; 
		PreparedStatement ps = null;
		ResultSet rs=null;
		Proveedor prove = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps =con.prepareStatement("select * from proveedores prov, tipo_documento td "
					+ "where prov.tipo_documento = td.codigo and nombre ilike ?");
			ps.setString(1, "%"+subcadena+"%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String identificador = rs.getString("identificador");
				String CodigoTipoDocumento = rs.getString("tipo_documento");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				String descripcionTD = rs.getString("descripcion");
				TipoDocumento td = new TipoDocumento(CodigoTipoDocumento,descripcionTD);
				
				prove = new Proveedor(identificador,td,nombre,telefono,correo,direccion);
				proveedores.add(prove);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: "+e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}
		
		return proveedores;
	} 
	
	public void insertar (Proveedor prov) throws KrakedevException{
		Connection con = null; 
		PreparedStatement ps = null;
		
		try {
			con = ConexionBDD.obtenerConexion();
			ps =con.prepareStatement("insert into proveedores(identificador,tipo_documento,nombre,telefono,correo,direccion) "+
			      "values (?,?,?,?,?,?)");
			ps.setString(1, prov.getIdentificador());
			ps.setString(2, prov.getTipoDocumento().getCodigo());
			ps.setString(3, prov.getNombre());
			ps.setString(4, prov.getTelefono());
			ps.setString(5, prov.getCorreo());
			ps.setString(6, prov.getDireccion());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: "+e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}
	} 
	
	
	public Proveedor buscarProveedor (String identificador) throws KrakedevException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proveedor prove = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = con.prepareStatement(
					"select prov.identificador, td.codigo, td.descripcion, prov.nombre, prov.telefono, prov.correo, prov.direccion "
					+ "from proveedores prov, tipo_documento td where identificador = ? and td.codigo = tipo_documento");
			ps.setString(1,identificador);
			rs = ps.executeQuery();

			while(rs.next()) {
				String CodigoTipoDocumento = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				String descripcionTD = rs.getString("descripcion");
				TipoDocumento td = new TipoDocumento(CodigoTipoDocumento,descripcionTD);
				
				prove = new Proveedor(identificador,td,nombre,telefono,correo,direccion);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: " + e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}

		return prove;
	}
}
