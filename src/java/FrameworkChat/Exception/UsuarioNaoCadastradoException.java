/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkChat.Exception;

/**
 *
 * @author Guilherme
 */
public class UsuarioNaoCadastradoException extends Exception{

    public UsuarioNaoCadastradoException() {
        super("O usuário não pode ser removido, pois não está cadastrado");
    }
    
    
    
}
