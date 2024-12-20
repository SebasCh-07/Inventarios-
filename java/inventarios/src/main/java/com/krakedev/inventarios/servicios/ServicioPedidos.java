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

import com.krakedev.inventarios.bdd.PedidosBDD;
import com.krakedev.inventarios.entidades.Pedido;
import com.krakedev.inventarios.excepciones.KrakedevException;

@Path("pedidos")
public class ServicioPedidos {
	
	@Path("registrar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Pedido pedido) {
		PedidosBDD producto = new PedidosBDD();
		try {
			producto.insertar(pedido);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	} 
	
	@Path("recibir")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response recibir(Pedido pedido) {
		PedidosBDD producto = new PedidosBDD();
		try {
			producto.actualizar(pedido);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	} 
	
	@Path("buscarPorProve/{sub}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar( @PathParam("sub") String identidicador) {
		PedidosBDD pedido = new PedidosBDD();
		ArrayList<Pedido> pedidos = null;
		try {
			pedidos = pedido.buscar(identidicador);
			return Response.ok(pedidos).build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	} 
}
