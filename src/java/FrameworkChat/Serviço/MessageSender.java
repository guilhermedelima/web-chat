/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkChat.Serviço;

import FrameworkChat.Exception.ChatNaoIniciadoException;
import FrameworkChat.Modelo.Chat;
import FrameworkChat.Modelo.Usuario;
import Service.OfflineMessageSender;
import Service.OnlineMessageSender;
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public abstract class MessageSender {
    
    protected ArrayList<Chat> conversas;
    
    public static MessageSender obterMessageSender(String op){
        
        op = op.toLowerCase();
        
        if(op.equals("online"))
            return OnlineMessageSender.getInstancia();
        else if(op.equals("offline"))
            return OfflineMessageSender.getInstancia();
        else
            return null;
    }
    
    public abstract void iniciarChat(Usuario remetente, Usuario Destinatario);
    public abstract void encerrarChat(String remetente, String destinatario);
    public abstract void enviarMensagem(String msg, String remetente, String chatRemetente, String chatDestinatario) throws ChatNaoIniciadoException;
    public abstract Chat obterChat(String remetente, String destinatario);
    public abstract ArrayList<Chat> obterSolicitacoesChats(String usuarioLogin);
    public abstract void finalizarSessao(String usuarioLogin);
    
    public ArrayList<Chat> getConversas() {
        return conversas;
    }

    protected void setConversas(ArrayList<Chat> conversas) {
        this.conversas = conversas;
    }
}
