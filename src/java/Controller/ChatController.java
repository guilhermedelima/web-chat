/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FrameworkChat.Controlador.ControladorCadastroUsuario;
import FrameworkChat.Exception.ChatNaoIniciadoException;
import FrameworkChat.Exception.UsuarioNaoAutenticadoException;
import FrameworkChat.Modelo.Chat;
import FrameworkChat.Modelo.Usuario;
import FrameworkChat.Serviço.Autenticador;
import FrameworkChat.Serviço.LoginUsuario;
import FrameworkChat.Serviço.MessageSender;
import Model.UsuarioVraptor;
import Service.OnlineMessageSender;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import java.util.ArrayList;
import static br.com.caelum.vraptor.view.Results.*;

/**
 *
 * @author guilherme
 */
@Resource
public class ChatController {
    
    private Result result;
    private Validator validator;
    private UsuarioVraptor user;

    public ChatController(Result result, Validator validator, UsuarioVraptor user) {
        this.result = result;
        this.validator = validator;
        this.user = user;
    }
    
    @Path("/chat/login")
    public void login(){ 
        if(user.isOnline())
            result.redirectTo(ChatController.class).inicioChat();
    }
    
    @Path("/chat/inicio")
    public void inicioChat(){ 
        result  .include("login", user.getUsuarioSessao().getLogin())
                .include("nome",user.getUsuarioSessao().getNome());
    }
    
    @Path("/chat/conversa")
    public void chatOnline(){ }
    
    //
    
    @Post
    @Path("/chat/efetuarLogin")
    public void efetuarLogin(String login, String senha){
        
        LoginUsuario uLogin = LoginUsuario.getInstancia();
        Autenticador autenticador = Autenticador.obterAutenticador("txt");
        
        if(autenticador.autenticarUsuario(login, senha)){
            ControladorCadastroUsuario c = ControladorCadastroUsuario.getInstancia();
            Usuario u =  c.consultar(login, senha, "txt");
            
            uLogin.efetuarLogin(u);
            user.setUsuarioSessao(u);
            
            result.redirectTo(ChatController.class).inicioChat();
        }else{
            UsuarioNaoAutenticadoException e = new UsuarioNaoAutenticadoException();
            validator.add(new ValidationMessage(e.getMessage(), "erroLogin"));
            validator.onErrorRedirectTo(IndexController.class).index();
        }
        
    }
    
    @Path("/chat/efetuarLogout")
    public void efetuarLogout(){
        String login = user.getUsuarioSessao().getLogin();
        String senha = user.getUsuarioSessao().getSenha();
        
        MessageSender sender = MessageSender.obterMessageSender("online");
        sender.finalizarSessao(login);
        
        LoginUsuario uLogin = LoginUsuario.getInstancia();
        uLogin.efetuarLogout(login, senha);
        
        user.logout();
        result.redirectTo(IndexController.class).index();
    }
    
    @Path("/chat/abrirChat")
    public void abrirChat(String destinatario){
        
        ControladorCadastroUsuario c = ControladorCadastroUsuario.getInstancia();
        
        Usuario uDestinatario = c.consultar(destinatario, "txt");
        Usuario uRemetente = user.getUsuarioSessao();
        
        MessageSender sender = MessageSender.obterMessageSender("online");
        sender.iniciarChat(uRemetente, uDestinatario);
        
        result.include("loginRemetente", uRemetente.getLogin());
        result.include("loginDestinatario", uDestinatario.getLogin());
        result.redirectTo(ChatController.class).chatOnline();
        
    }
    
    @Path("/chat/aceitarChat")
    public void aceitarChat(String remetente){
        
        ControladorCadastroUsuario c = ControladorCadastroUsuario.getInstancia();
        
        result.include("loginRemetente", remetente);
        result.include("loginDestinatario", user.getUsuarioSessao().getLogin());
        result.redirectTo(ChatController.class).chatOnline();
        
    }
    
    //
    
    
    @Get
    @Path("/chat/ajax/usuarios")
    public void ajaxUsuariosOnline(){
        LoginUsuario uLogin = LoginUsuario.getInstancia();
        ArrayList<Usuario> lista = uLogin.getUsuariosOnline();
        result.use(json()).from(lista).serialize();
    }
    
    @Get
    @Path("/chat/ajax/solicitacoes")
    public void ajaxSolicitacoes(String loginSessao){
        
        MessageSender sender = MessageSender.obterMessageSender("online");
        ArrayList<Chat> solicitacaoChat = sender.obterSolicitacoesChats(loginSessao);
        
        result.use(json()).from(solicitacaoChat).include("remetente").include("destinatario").include("mensagens").serialize();
    }
    
    @Get
    @Path("/chat/ajax/chatOnline")
    public void ajaxChatRequisicao(String remetente, String destinatario){
        
        MessageSender sender = MessageSender.obterMessageSender("online");
        Chat c = sender.obterChat(remetente, destinatario);
        
        result.use(json()).from(c).include("remetente").include("destinatario").include("mensagens").serialize();
    }
    
    
    @Path("/chat/ajax/atualizarChat")
    public void atualizarChat(String msg, String remetente, String destinatario) throws ChatNaoIniciadoException{
        
        System.out.println(msg);
        System.out.println(remetente);
        System.out.println(destinatario);   
        
        MessageSender sender = MessageSender.obterMessageSender("online");
        sender.enviarMensagem(msg, user.getUsuarioSessao().getLogin(), remetente, destinatario);
        result.use(Results.json()).from("OK").serialize();
    }
    
    
    
}
