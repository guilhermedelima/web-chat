/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkChat.Serviço;

import FrameworkChat.Exception.OperacaoArquivoException;
import FrameworkChat.Exception.UsuarioNaoAutenticadoException;
import FrameworkChat.Modelo.Usuario;
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public class LoginUsuario {
    
    static LoginUsuario instancia;
    private ArrayList<Usuario> usuariosOnline;
    
    public static LoginUsuario getInstancia(){
        
        if(instancia == null){
            instancia = new LoginUsuario();
        }
        return instancia;
    }

    public LoginUsuario(){ 
        this.usuariosOnline = new ArrayList<Usuario>();
    }
    
    public void efetuarLogin(Usuario u){
        adicionarUsuarioOnline(u);
    }
    
    public void efetuarLogout(String login, String senha){
        excluirUsuarioOnline(login, senha);
    }
    
    private void adicionarUsuarioOnline(Usuario u){
        this.usuariosOnline.add(u);
    }
    
    private void excluirUsuarioOnline(String login, String senha){
        for(Usuario temp : this.usuariosOnline){
            if(login.equals(temp.getLogin()) && senha.equals(temp.getSenha())){
                this.usuariosOnline.remove(temp);
                break;
            }
        }
    }
    
    public ArrayList<Usuario> getUsuariosOnline(){
        return this.usuariosOnline;
    }
}
