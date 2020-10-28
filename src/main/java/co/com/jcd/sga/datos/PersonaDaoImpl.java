package co.com.jcd.sga.datos;

import co.com.jcd.sga.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PersonaDaoImpl implements PersonaDao{
    
    @PersistenceContext(unitName = "SgaPU")
    EntityManager em;

    @Override
    public List<Persona> findAdllPersonas() {
        return em.createNamedQuery("Persona.findAll").getResultList();
    }

    @Override
    public Persona findPersonaById(Persona persona) {
        return em.find(Persona.class, persona.getIdPersona());
    }

    @Override
    public Persona findPersonaByEmail(Persona persona) {
        Query query = em.createQuery("from Persona p where p.email =: email");
        query.setParameter("email", persona.getEmail());
        return (Persona) query.getSingleResult();
    }

    @Override
    public void insertPersona(Persona persona) {
        em.persist(persona);
    }

    @Override
    public void deletePersona(Persona persona) {
        em.remove(em.merge(persona)); // primero actualiza el estado del objeto en bd para que se sincronice el estado
    }

    @Override
    public void updatePersona(Persona persona) {
        em.merge(persona);
    }
    
}