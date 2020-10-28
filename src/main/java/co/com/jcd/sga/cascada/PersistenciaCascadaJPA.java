/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jcd.sga.cascada;

import co.com.jcd.sga.domain.Persona;
import co.com.jcd.sga.domain.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jcd
 */
public class PersistenciaCascadaJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String args[]){
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // paso 1: crear nuevo objeto
        // objeto en estado transitivo
        Persona persona1 = new Persona("Zoila","Cerda","zoila123@correo.com",
                "5555555");
        // paso 2: crear objeto usuario (tiene dependencia con el objeto persona)
        Usuario usuario1 = new Usuario("zoilacerda", "1234", persona1);
        // paso 3: persistimos el objeto usuario1
        em.persist(usuario1);
        // paso 4: commit transaccion
        tx.commit();
        // objetos en estado detached
        log.debug("Objeto persistido persona: "+persona1);
        log.debug("Objeto persistido usuario: "+usuario1);
        
        em.close();
    }
}
