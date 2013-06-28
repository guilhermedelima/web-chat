/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkChat.Modelo;

import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public class Chat {
    
    private Usuario remetente;
    private Usuario destinatario;
    ArrayList<Mensagem> mensagens;

    public Chat(Usuario remetente, Usuario destinatario) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.mensagens = new ArrayList<Mensagem>();
    }
    
    public void enviarMensagem(String texto, String nomeRemetente){
        Mensagem msg = new Mensagem(nomeRemetente, texto);
        this.mensagens.add(msg);
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }
    
    public ArrayList<Mensagem> getMensagens() {
        return mensagens;
    }
}
