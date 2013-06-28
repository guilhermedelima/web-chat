/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkChat.Controlador;

import FrameworkChat.DAO.AbstractDAO;
import FrameworkChat.Exception.OperacaoArquivoException;
import FrameworkChat.Exception.UsuarioJaCadastradoException;
import FrameworkChat.Exception.UsuarioNaoCadastradoException;
import FrameworkChat.Modelo.Usuario;
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public class ControladorCadastroUsuario {
    
    private AbstractDAO txtDAO, xmlDAO;
    static ControladorCadastroUsuario instancia;
    
    public static ControladorCadastroUsuario getInstancia(){
        if(instancia == null){
            instancia = new ControladorCadastroUsuario();
            instancia.txtDAO = AbstractDAO.obterDAO("txt");
            instancia.xmlDAO = AbstractDAO.obterDAO("xml");
        }
        
        return instancia;
    }
    
    private ControladorCadastroUsuario(){ }
    
    public void cadastrar(String login, String senha, String nome, int idade) throws UsuarioJaCadastradoException, OperacaoArquivoException{
        
        Usuario u = new Usuario(login, senha, nome, idade);
        txtDAO.salvarUsuario(u);
        xmlDAO.salvarUsuario(u);
        
    }
    
    public void excluir(String login, String senha) throws UsuarioNaoCadastradoException, OperacaoArquivoException{
        
        txtDAO.excluirUsuario(login, senha);
        xmlDAO.excluirUsuario(login, senha);
        
    }
    
    public void alterar(String login, String senhaAntiga, String novaSenha, String novoNome, int novaIdade) throws OperacaoArquivoException{
        
        Usuario novoUsuario = new Usuario(login, novaSenha, novoNome, novaIdade);
        txtDAO.alterarUsuario(login, senhaAntiga, novoUsuario);
        xmlDAO.alterarUsuario(login, senhaAntiga, novoUsuario);
        
    }
    
    public Usuario consultar(String login, String senha, String tipoArquivo){
        
        tipoArquivo = tipoArquivo.toLowerCase();
        Usuario u;
                
        if(tipoArquivo.equals("txt")){
            u = txtDAO.consultarUsuario(login, senha);
            return u;
        }else if(tipoArquivo.equals("xml")){
            u = xmlDAO.consultarUsuario(login, senha);
            return u;
        }
        return null;
    }
    
    public Usuario consultar(String login, String tipoArquivo){
        
        tipoArquivo = tipoArquivo.toLowerCase();
        Usuario u;
                
        if(tipoArquivo.equals("txt")){
            u = txtDAO.consultarUsuario(login);
            return u;
        }else if(tipoArquivo.equals("xml")){
            u = xmlDAO.consultarUsuario(login);
            return u;
        }
        return null;
    }
    
    public ArrayList<Usuario> consultarCadastro(String tipoArquivo){
        
        tipoArquivo = tipoArquivo.toLowerCase();
        ArrayList<Usuario> lista;
        
        if(tipoArquivo.equals("txt")){
            lista = txtDAO.obterCadastro();
            return lista;
        }else if(tipoArquivo.equals("xml")){
            lista = xmlDAO.obterCadastro();
            return lista;
        }
        return null;
    }
}
