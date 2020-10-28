/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jcd.sga.datos;

import co.com.jcd.sga.domain.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jcd
 */
public class UsuarioDaoImpl implements UsuarioDao{
    
    @PersistenceContext(unitName = "SgaPU")
    EntityManager em;

    @Override
    public List<Usuario> findAllUsuarios() {
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }

    @Override
    public Usuario findUsuarioById(Usuario usuario) {
        return em.find(Usuario.class, usuario.getIdUsuario());
    }

    @Override
    public Usuario findUsuarioByUsername(Usuario usuario) {
        return (Usuario) em.createNamedQuery("Usuario.findByUsername")
                .getSingleResult();
    }

    @Override
    public Usuario findUsuarioByPassword(Usuario usuario) {
        Query query = em.createQuery("from Usuario u where u.password =: password");
        query.setParameter("password", usuario.getPassword());
        return (Usuario) query.getSingleResult();
    }

    @Override
    public void insertUsuario(Usuario usuario) {
        em.persist(usuario);
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        em.remove(em.merge(usuario));
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        em.merge(usuario);
    }
    
}
