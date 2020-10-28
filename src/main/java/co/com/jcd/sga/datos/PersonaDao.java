/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jcd.sga.datos;

import co.com.jcd.sga.domain.Persona;
import java.util.List;

/**
 *
 * @author jcd
 */
public interface PersonaDao {
    
    public List<Persona> findAdllPersonas();
    
    public Persona findPersonaById(Persona persona);
    
    public Persona findPersonaByEmail(Persona persona);
    
    public void insertPersona(Persona persona);
    
    public void deletePersona(Persona persona);
    
    public void updatePersona(Persona persona);
    
}
