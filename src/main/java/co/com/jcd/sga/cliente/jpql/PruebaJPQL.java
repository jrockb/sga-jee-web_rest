
package co.com.jcd.sga.cliente.jpql;

import co.com.jcd.sga.domain.Persona;
import co.com.jcd.sga.domain.Usuario;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jcd
 */
public class PruebaJPQL {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String args[]){
        String jpql = null;
        Query q = null;
        List<Persona> personas = null;
        Persona persona = null;
        Iterator iter = null;
        Object[] tupla = null;
        List<String> nombres = null;
        List<Usuario> usuarios = null;
        
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        //1. Consulta de todos los objetos de tipo persona
        log.debug("\n1. Consulta de todas las personas");
        jpql = "select p from Persona p";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        //2. Consulta de la persona con id = 1
        log.debug("\n2. Consulta de la persona con id = 1");
        jpql = "select p from Persona p where p.idPersona = 1";
        persona = (Persona) em.createQuery(jpql).getSingleResult();
        log.debug(persona);
        
        //3. Consulta de la persona por nombre
        log.debug("\n3. Consulta de la persona con nombre = Jonny");
        jpql = "select p from Persona p where p.nombre = 'Jonny'"; // varias personas pueden tener el mismo nombre
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        //4. Consulta de datos individuales, se crea un arreglo(tupla) de tipo object de 3 columnas 
        log.debug("\n4. se crea un arreglo(tupla) de tipo object de 3 columnas");
        jpql = "select p.nombre as Nombre, p.apellido as Apellido, p.email as Email "
                + "from Persona p"; // varias personas pueden tener el mismo nombre
        iter = em.createQuery(jpql).getResultList().iterator(); //para iterar los elementos
        while(iter.hasNext()){
            tupla = (Object[]) iter.next();
            String nombre = (String) tupla[0];
            String apellido = (String) tupla[1];
            String email = (String) tupla[2];
            log.debug("Nombre: "+nombre+", Apellido: "+apellido+", Email: "+email);
        }
        
        //5. Obtiene el objeto persona y el id
        log.debug("\n5. Obtiene el objeto persona y el id");
        jpql = "select p, p.idPersona from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while(iter.hasNext()){
            tupla = (Object[]) iter.next();
            persona = (Persona) tupla[0];
            int idPersona = (int) tupla[1];
            log.debug("Objeto persona: "+persona);
            log.debug("Id persona: "+idPersona);
        }
        
        //6. Consulta de todas las personas
        log.debug("\n6. Obtiene el objeto persona y el id");
        jpql = "select new co.com.jcd.sga.domain.Persona(p.idPersona) "
                + "from Persona p"; // se usa cuando se requiere todo el objeto pero solo con la llave primaria
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        //7. Regresa el valor maximo y el valor minimo
        log.debug("\n7. Regresa el valor maximo y el valor minimo");
        jpql = "select min(p.idPersona) as MinId, max(p.idPersona) as MaxId, "
                + "count(p.idPersona) as Contador from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while(iter.hasNext()){
            tupla = (Object[]) iter.next();
            Integer idMin = (Integer) tupla[0];
            Integer idMax = (Integer) tupla[1];
            Long cuenta = (Long) tupla[2];
            log.debug("idMin: "+idMin+", idMax: "+idMax+", count: "+cuenta);            
        }
        
        //8. Cuenta los nombres de las personas que son distintos
        log.debug("\n8. Cuenta los nombres de las personas que son distintos");
        jpql = "select count(distinct p.nombre) from Persona p";
        Long contador  =  (Long) em.createQuery(jpql).getSingleResult();
        log.debug("personas con nombres distintos: "+contador);
        
        //9. Obtiene el objeto persona con id igual al parametro proporcionado
        log.debug("\n9. Obtiene el objeto persona con id igual al parametro proporcionado");
        int idPersona= 3;
        jpql = "select p from Persona p where p.idPersona = :id";
        q = em.createQuery(jpql);
        q.setParameter("id", idPersona);
        persona = (Persona) q.getSingleResult();
        log.debug(persona);
        
        //10. uso de subquery
        log.debug("\n10. uso de subquery");
        jpql = "select p from Persona p where p.idPersona in (select "
                + "max(p1.idPersona) from Persona p1)";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        //11. Uso de join con lazy loading
        log.debug("\n11. Uso de join con lazy loading");
        jpql = "select u from Usuario u join u.persona p";
        usuarios = em.createQuery(jpql).getResultList();
        mostrarUsuarios(usuarios);
        
        //12. Uso de left join con eager loading
        log.debug("\n12. Uso de left join con eager loading");
        jpql = "select u from Usuario u left join fetch u.persona"; // con fetch se usa eager
        usuarios = em.createQuery(jpql).getResultList();
        mostrarUsuarios(usuarios);
        
    }

    private static void mostrarPersonas(List<Persona> personas) {
        for(Persona p: personas){
            log.debug(p);
        }
    }
    
    private static void mostrarUsuarios(List<Usuario> usuarios) {
        for(Usuario u: usuarios){
            log.debug(u);
        }
    }
    
}
