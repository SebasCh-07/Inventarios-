package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.CategoriasBDD;
import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.excepciones.KrakedevException;

@Path("categorias")
public class ServiciosCategoria {
	
	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Categoria cat) {
		CategoriasBDD categ = new CategoriasBDD();
		try {
			categ.insertar(cat);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	} 
	
	@Path("actualizar")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response recibir(Categoria cat) {
		CategoriasBDD categ = new CategoriasBDD();
		try {
			categ.actualizar(cat);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	} 
	
	@Path("buscar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar() {
		CategoriasBDD cat = new CategoriasBDD();
		ArrayList<Categoria> categs = null;
		try {
			categs = cat.buscar();
			return Response.ok(categs).build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	} 
}
