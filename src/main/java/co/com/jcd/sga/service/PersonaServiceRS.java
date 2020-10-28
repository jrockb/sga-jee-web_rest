
package co.com.jcd.sga.service;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


import co.com.jcd.sga.domain.Persona;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/personas") //path para poder acceder al webservice
@Stateless //ejb que se va exponer con el api de JAX-JS
public class PersonaServiceRS {
    
    @Inject //para inyectar una instancia del servicio PersonaService
    private PersonaService personaService;
    
    @GET //para obtener informacion
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) //como son mas de un elemento se usan llaves
    public List<Persona> listarPersonas(){
        return personaService.listarPersona();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{id}") //para que sea la operacion por defecto al llamar el ws
    public Persona encontrarPersonaPorId(@PathParam("id") int id){
        return personaService.encontrarPersonaPorId(new Persona(id)); //en caso de que exista la persona se va devolver por este metodo
    }
    
    @POST //se va agregar una persona al servidor
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response agregarPersona(Persona persona){
        try{
            personaService.registrarPersona(persona);
            return Response.ok().entity(persona).build();
        } catch(Exception ex){
            ex.printStackTrace(System.out);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PUT 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response modificarPersona(@PathParam("id") int id, Persona personaModificada){
        try{
            Persona persona  = personaService.encontrarPersonaPorId(new Persona(id));
            if(persona != null){
                personaService.modificarPersona(personaModificada);
                return Response.ok().entity(personaModificada).build();
            }else{ // si la persona es null no existe en la base de datos
                return Response.status(Status.NOT_FOUND).build();
            }
        }catch(Exception ex){
            ex.printStackTrace(System.out);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminarPersonaPorId(@PathParam("id") int id){
        try{
            personaService.eliminarPersona(new Persona(id));
            return Response.ok().build();
        }catch(Exception ex){
            ex.printStackTrace(System.out);
            return Response.status(404).build();
        }
    }
}
