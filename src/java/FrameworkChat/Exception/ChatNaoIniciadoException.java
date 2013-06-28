/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkChat.Exception;

/**
 *
 * @author guilherme
 */
public class ChatNaoIniciadoException extends Exception{

    public ChatNaoIniciadoException() {
        super("O chat não foi iniciado");
    }
    
}
