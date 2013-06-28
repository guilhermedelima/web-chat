/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import FrameworkChat.Controlador.ControladorCadastroUsuario;
import FrameworkChat.Exception.UsuarioNaoAutenticadoException;
import FrameworkChat.Modelo.Usuario;
import FrameworkChat.Serviço.Autenticador;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
@Resource
public class CadastroController {

	private Result result;
        private final Validator validator;

	public CadastroController(Result result, Validator validator) {
		this.result = result;
                this.validator = validator;
	}
        
        @Path("/cadastro/incluir")
        public void cadastro() { }
        
        @Path("/cadastro/alterar")
        public void cadastroAlteracao(){ }
        
        @Path("/cadastro/autenticar")
        public void autenticacaoCadastro(){ }
        
        @Path("/cadastro/listagem")
        public void cadastroListagem(){ }
        
        //
        
        @Post
        @Path("/cadastro/cadastrarUsuario")
        public void cadastrarUsuario(String login, String senha, String nome, String idade){
            
            ControladorCadastroUsuario controlador = ControladorCadastroUsuario.getInstancia();
            int idadeInt = Integer.parseInt(idade);
            
            try{
                controlador.cadastrar(login, senha, nome, idadeInt);
                result.redirectTo(IndexController.class).cadastroIndex();
            }catch(Exception e){
                validator.add(new ValidationMessage(e.getMessage(), "erro1"));
            }    
            
            validator.onErrorRedirectTo(CadastroController.class).cadastro();
            
        }
        
        @Post
        @Path("/cadastro/autenticarCadastro")
        public void autenticarCadastro(String login, String senha, String operacao){

            try{
                if(!operacao.equals("alterar") && !operacao.equals("excluir")){
                    validator.add(new ValidationMessage("Operacao inválida, tente novamente", "erro2"));
                }
            }catch(NullPointerException e){
                validator.add(new ValidationMessage("Operacao inválida, tente novamente", "erro2"));
            }
            
            validator.onErrorRedirectTo(IndexController.class).cadastroIndex();
            
            
            Autenticador a = Autenticador.obterAutenticador("xml");
            if(a.autenticarUsuario(login, senha)){
                ControladorCadastroUsuario c = ControladorCadastroUsuario.getInstancia();
                Usuario u = c.consultar(login, senha, "xml");
                result     .include("loginUsuario", u.getLogin())
                           .include("senhaUsuario", u.getSenha())
                           .include("nomeUsuario", u.getNome())
                           .include("idadeUsuario", u.getIdade())
                           .include("operacao", operacao);

                result.redirectTo(CadastroController.class).cadastroAlteracao();
                return;
                
            }else{
                UsuarioNaoAutenticadoException e = new UsuarioNaoAutenticadoException();
                validator.add(new ValidationMessage(e.getMessage(), "erro2"));
            }
            
            validator.onErrorRedirectTo(IndexController.class).cadastroIndex();
             
        }
        
        @Post
        @Path("/cadastro/excluirUsuario")
        public void excluirUsuario(String login, String senha){
            
            ControladorCadastroUsuario controlador = ControladorCadastroUsuario.getInstancia();
            
            try{
                controlador.excluir(login, senha);
                result.redirectTo(IndexController.class).cadastroIndex();
                
            }catch(Exception e){
                validator.add(new ValidationMessage(e.getMessage(), "erro3"));
            }
            
            validator.onErrorRedirectTo(CadastroController.class).cadastroAlteracao();
            return;
            
        }
        
        @Post
        @Path("/cadastro/alterarUsuario")
        public void alterarUsuario(String login, String senha, String novaSenha, String nome, String idade){
            
            ControladorCadastroUsuario controlador = ControladorCadastroUsuario.getInstancia();
            int i = Integer.parseInt(idade);
            
            try{
                controlador.alterar(login, senha, novaSenha, nome, i);
                result.redirectTo(IndexController.class).cadastroIndex();
                
            }catch(Exception e){
                validator.add(new ValidationMessage(e.getMessage(), "erro4"));
            }
            
            validator.onErrorRedirectTo(CadastroController.class).cadastroAlteracao();
            return;
            
        }
        
        @Path("/cadastro/listarCadastro")
        public void listarCadastro(){
            
            ControladorCadastroUsuario controlador = ControladorCadastroUsuario.getInstancia();
            ArrayList<Usuario> lista = controlador.consultarCadastro("xml");
            
            if(!lista.isEmpty()){
                result.include("lista", lista);
                result.redirectTo(CadastroController.class).cadastroListagem();
            }else{
                validator.add(new ValidationMessage("Nao há usuarios cadastrados", "erro5"));
                validator.onErrorRedirectTo(CadastroController.class).cadastroAlteracao();
            }
            
            return;
        }

}
