package mx.com.gm.sga.servicio.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.jsp.tagext.TryCatchFinally;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.servicio.PersonaService;

@Path("/persona")
@Stateless
public class PersonaServiceRS {
	
	@Inject
	private PersonaService personaService;
	
	                  /////tener Lista de persona 
	@GET
	@Produces(value={MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
	public List<Persona> listarPersonas(){
		return personaService.listarPersonas();
		
	}
	
	                  ///////tener persona por id
	@GET
	@Produces(value={MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
	@Path("{ id }")
	public Persona encontrarPersonaPorId(@PathParam("id")int id) {
		return personaService.encontrarPersonaPorId(new Persona(id));
	}
	
	                    ///////crear persona 
	@POST
	@Produces(value={MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
	@Consumes(value={MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
	
	public Response registrarPersona(Persona persona) {
		
	try {
		personaService.registrarPersona(persona);
		return Response.ok().entity(persona).build();	
	} catch (Exception e) {
	   return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}
		
	}
	                  //////////////modificar perosna 
	
	@PUT
	@Produces(value={MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
	@Consumes(value={MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON })
	@Path("{ id }")
	
	public Response modificarPersona(@PathParam("id")int id ,Persona personaModificada) {
		try {
			Persona persona=personaService.encontrarPersonaPorId(new Persona(id));
			if(persona != null) {
				personaService.modificarPersona(personaModificada);
				return Response.ok().entity(persona).build();
			}else {
				return Response.status(Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("{ id }")
	public Response eliminarPersona(@PathParam("id")int id) {
		
		try {
			personaService.eliminarPersona(new Persona(id));
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(404).build(); 
		}
	}
	

}










