package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProductosBDD;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.excepciones.KrakedevException;

@Path("productos")
public class ServiciosProducto {

	@Path("buscar/{sub}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar( @PathParam("sub") String subcadena) {
		ProductosBDD produ = new ProductosBDD();
		ArrayList<Producto> Productos = null;
		try {
			Productos = produ.buscar(subcadena);
			return Response.ok(Productos).build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	} 
	
	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Producto produ) {
		ProductosBDD producto = new ProductosBDD();
		try {
			producto.insertar(produ);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	} 
	

	@Path("actualizar")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response recibir(Producto produ) {
		ProductosBDD producto = new ProductosBDD();
		try {
			producto.actualizar(produ);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	} 
	
	@Path("buscarProdu/{sub}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarProducto( @PathParam("sub") int codigo) {
		ProductosBDD produ = new ProductosBDD();
		Producto Producto = null;
		try {
			Producto = produ.buscarProducto(codigo);
			return Response.ok(Producto).build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	} 
}
