
package co.com.jcd.sga.service;

import co.com.jcd.sga.datos.PersonaDao;
import co.com.jcd.sga.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

/**
 *
 * @author jcd
 */
@Stateless
@WebService(endpointInterface = "co.com.jcd.sga.service.PersonaServiceWS")
public class PersonaServiceImpl implements PersonaServiceRemoto,PersonaService,
        PersonaServiceWS {
    
    @Inject
    private PersonaDao personaDao;
    
    @Override
    public List<Persona> listarPersona() {
        return personaDao.findAdllPersonas();
    }

    @Override
    public Persona encontrarPersonaPorId(Persona persona) {
        return personaDao.findPersonaById(persona);
    }

    @Override
    public Persona encontrarPersonaPorEmail(Persona persona) {
        return personaDao.findPersonaByEmail(persona);
    }

    @Override
    public void registrarPersona(Persona persona) {
        personaDao.insertPersona(persona);
    }

    @Override
    public void modificarPersona(Persona persona) {
        personaDao.updatePersona(persona);
    }

    @Override
    public void eliminarPersona(Persona persona) {
        personaDao.deletePersona(persona);
    }    
    
    
}
