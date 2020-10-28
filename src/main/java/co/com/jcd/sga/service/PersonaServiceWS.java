
package co.com.jcd.sga.service;

import co.com.jcd.sga.domain.Persona;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface PersonaServiceWS {
    
    @WebMethod
    public List<Persona> listarPersona();
    
}
