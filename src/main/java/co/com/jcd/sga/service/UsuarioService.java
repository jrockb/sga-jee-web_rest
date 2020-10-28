/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jcd.sga.service;

import co.com.jcd.sga.domain.Usuario;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jcd
 */
@Remote
public interface UsuarioService {
    
    public List<Usuario> findAllUsuarios();
    
    public Usuario findUsuarioById(Usuario usuario);
    
    public Usuario findUsuarioByUsername(Usuario usuario);

    public Usuario findUsuarioByPassword(Usuario usuario);

    public void insertUsuario(Usuario usuario);
    
    public void deleteUsuario(Usuario usuario);
    
    public void updateUsuario(Usuario usuario);
    
}
