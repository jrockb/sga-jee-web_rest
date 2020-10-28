/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jcd.sga.service;

import co.com.jcd.sga.datos.UsuarioDao;
import co.com.jcd.sga.domain.Usuario;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jcd
 */
public class UsuarioServiceImpl implements UsuarioService {
    
    @Inject
    UsuarioDao usuarioDao;

    @Override
    public List<Usuario> findAllUsuarios() {
        return usuarioDao.findAllUsuarios();
    }

    @Override
    public Usuario findUsuarioById(Usuario usuario) {
        return usuarioDao.findUsuarioById(usuario);
    }

    @Override
    public Usuario findUsuarioByUsername(Usuario usuario) {
        return usuarioDao.findUsuarioByUsername(usuario);
    }

    @Override
    public Usuario findUsuarioByPassword(Usuario usuario) {
        return usuarioDao.findUsuarioByPassword(usuario);
    }

    @Override
    public void insertUsuario(Usuario usuario) {
        usuarioDao.insertUsuario(usuario);
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        usuarioDao.deleteUsuario(usuario);
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        usuarioDao.updateUsuario(usuario);
    }
    
}
