package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.excepciones.KrakedevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class TipoDocumentoBDD {
	public ArrayList<TipoDocumento> buscar() throws KrakedevException{
		ArrayList<TipoDocumento> tipoDocumentos = new ArrayList<TipoDocumento>();
		Connection con = null; 
		PreparedStatement ps = null;
		ResultSet rs=null;
		TipoDocumento tD = null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps =con.prepareStatement("select * from tipo_documento");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String codigo = rs.getString("codigo");
				String descripcion = rs.getString("descripcion");
			
				
				tD = new TipoDocumento(codigo,descripcion);
				tipoDocumentos.add(tD);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al consultar. Detalle: "+e.getMessage());
		} catch (KrakedevException e) {
			throw e;
		}
		
		return tipoDocumentos;
	} 
}
