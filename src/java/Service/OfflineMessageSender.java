/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import FrameworkChat.Exception.ChatNaoIniciadoException;
import FrameworkChat.Modelo.Chat;
import FrameworkChat.Modelo.Usuario;
import FrameworkChat.Serviço.MessageSender;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author guilherme
 */
public class OfflineMessageSender extends MessageSender{
    
    private static OfflineMessageSender instancia;
    
    public static OfflineMessageSender getInstancia(){
        
        if(instancia == null){
            instancia = new OfflineMessageSender();
        }
        
        return instancia;
    }
    
    private OfflineMessageSender(){
        this.conversas = new ArrayList<Chat>();
    }

    @Override
    public void iniciarChat(Usuario remetente, Usuario Destinatario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void encerrarChat(String remetente, String destinatario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void enviarMensagem(String msg, String remetente, String chatRemetente, String chatDestinatario) throws ChatNaoIniciadoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Chat obterChat(String remetente, String destinatario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Chat> obterSolicitacoesChats(String usuarioLogin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void finalizarSessao(String uLogout) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
    
}
