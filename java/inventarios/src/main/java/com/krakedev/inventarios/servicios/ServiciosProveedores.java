package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.excepciones.KrakedevException;

@Path("proveedores")
public class ServiciosProveedores {
	
	@Path("buscar/{sub}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar( @PathParam("sub") String subcadena) {
		ProveedoresBDD prove = new ProveedoresBDD();
		ArrayList<Proveedor> proveedores = null;
		try {
			proveedores = prove.buscar(subcadena);
			return Response.ok(proveedores).build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	} 
	
	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Proveedor prov) {
		ProveedoresBDD prove = new ProveedoresBDD();
		try {
			prove.insertar(prov);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	} 
	
	@Path("buscarProveedor/{sub}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarProve( @PathParam("sub") String identidicador) {
		ProveedoresBDD proveedor = new ProveedoresBDD();
		Proveedor prov = null;
		try {
			prov = proveedor.buscarProveedor(identidicador);
			return Response.ok(prov).build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	} 
}
