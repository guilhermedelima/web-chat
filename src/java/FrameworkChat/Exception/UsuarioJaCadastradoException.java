/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkChat.Exception;

/**
 *
 * @author Guilherme
 */
public class UsuarioJaCadastradoException extends Exception{

    public UsuarioJaCadastradoException() {
        super("O usuário já está cadastrado no Sistema");
    }
    
    
    
}
