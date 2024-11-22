package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Proveedor;
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
			ps =con.prepareStatement("select * from proveedores "
					+ "where nombre ilike ?");
			ps.setString(1, "%"+subcadena+"%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String identificador = rs.getString("identificador");
				String tipoDocumento = rs.getString("tipo_documento");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				
				
				prove = new Proveedor(identificador,tipoDocumento,nombre,telefono,correo,direccion);
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
}
