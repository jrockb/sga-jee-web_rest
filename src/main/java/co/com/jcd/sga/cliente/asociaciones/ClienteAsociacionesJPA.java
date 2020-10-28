package co.com.jcd.sga.cliente.asociaciones;

import co.com.jcd.sga.domain.Persona;
import co.com.jcd.sga.domain.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author jcd
 */
public class ClienteAsociacionesJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String args[]){
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        List<Persona> personas = em.createNamedQuery("Persona.findAll")
                .getResultList();
        
        em.close();
        
        for(Persona persona: personas){
            log.debug("Persona: "+persona);
            for(Usuario usuario : persona.getUsuarioList()){
                log.debug("Usuario: "+usuario);
            }
        }
    
    }
    
}
