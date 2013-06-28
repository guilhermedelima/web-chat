/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkChat.Exception;

/**
 *
 * @author guilherme
 */
public class UsuarioNaoAutenticadoException extends Exception{

    public UsuarioNaoAutenticadoException() {
        super("O usuário não está autenticado no sistema");
    }
    
    
    
}
