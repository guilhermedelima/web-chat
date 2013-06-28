/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import FrameworkChat.Modelo.Usuario;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

/**
 *
 * @author guilherme
 */
@Component
@SessionScoped
public class UsuarioVraptor {
    
    private Usuario usuarioSessao;
    boolean online;
    
    public boolean isOnline(){
        return (usuarioSessao != null);
    }
    
    public void logout(){
        this.usuarioSessao = null;
    }

    public Usuario getUsuarioSessao() {
        return usuarioSessao;
    }

    public void setUsuarioSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
    }
    
    
}
