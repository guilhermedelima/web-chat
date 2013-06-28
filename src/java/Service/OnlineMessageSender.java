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
public class OnlineMessageSender extends MessageSender{
    
    private static OnlineMessageSender instancia;
    
    public static OnlineMessageSender getInstancia(){
        
        if(instancia == null){
            instancia = new OnlineMessageSender();
        }
        
        return instancia;
    }
    
    private OnlineMessageSender(){
        this.conversas = new ArrayList<Chat>();
    }
 
    @Override
    public void iniciarChat(Usuario remetente, Usuario destinatario){
        
        for(Chat temp : getConversas()){
            
            String us1 = temp.getRemetente().getLogin();
            String us2 = temp.getDestinatario().getLogin();
            
            boolean teste1 = us1.equals(remetente.getLogin());
            boolean teste2 = us2.equals(destinatario.getLogin());
            
            if(teste1 && teste2)
                return;
        }
        
        Chat c = new Chat(remetente, destinatario);
        conversas.add(c);
        
    }
    
    @Override
    public void encerrarChat(String remetente, String destinatario){
        
        ArrayList<Chat> cadastroChat = getConversas();
        
        for(Chat temp : cadastroChat){
            
            String us1 = temp.getRemetente().getLogin();
            String us2 = temp.getDestinatario().getLogin();
            
            boolean teste1 = us1.equals(remetente);
            boolean teste2 = us2.equals(destinatario);

            if(teste1 && teste2){
                cadastroChat.remove(temp);
                setConversas(cadastroChat);
                return;
            }
        }
        
    }
    
    @Override
    public void enviarMensagem(String msg, String remetente, String chatRemetente, String chatDestinatario) throws ChatNaoIniciadoException{
        
        ArrayList<Chat> cadastroChat = getConversas();
        
        for(Chat temp : cadastroChat){
            
            String us1 = temp.getRemetente().getLogin();
            String us2 = temp.getDestinatario().getLogin();
            
            boolean teste1 = us1.equals(chatRemetente);
            boolean teste2 = us2.equals(chatDestinatario);
            
            if(teste1 && teste2){
                cadastroChat.remove(temp);
                temp.enviarMensagem(msg, remetente);
                cadastroChat.add(temp);
                setConversas(cadastroChat);
                return;
            }
        }
        
        throw new ChatNaoIniciadoException();
    }
    
    @Override
    public Chat obterChat(String remetente, String destinatario){
        
        for(Chat temp : getConversas()){
            
            String us1 = temp.getRemetente().getLogin();
            String us2 = temp.getDestinatario().getLogin();
            
            boolean teste1 = us1.equals(remetente);
            boolean teste2 = us2.equals(destinatario);
            
            if(teste1 && teste2){
                return temp;
            }
        }
        
        return null;
    }
    
    @Override
    public void finalizarSessao(String usuarioLogin) {
        
        ArrayList<Chat> cadastroChat = getConversas();
        Iterator i = cadastroChat.iterator();
        
        while(i.hasNext()){
            Chat temp = (Chat) i.next();
            
            String us1 = temp.getRemetente().getLogin();
            String us2 = temp.getDestinatario().getLogin();
            
            boolean participaChat = us1.equals(usuarioLogin) || us2.equals(usuarioLogin);
            
            if(participaChat){
                i.remove();
                setConversas(cadastroChat);
            }
        }
    }

    @Override
    public ArrayList<Chat> obterSolicitacoesChats(String usuarioLogin) {
        
        ArrayList<Chat> allChats = getConversas();
        ArrayList<Chat> userChats = new ArrayList<Chat>();
        
        for(Chat temp : allChats){
            
            String u = temp.getDestinatario().getLogin();
            
            if(u.equals(usuarioLogin))
                userChats.add(temp);
            
        }
        
        return userChats;
    }
    
}
    
    