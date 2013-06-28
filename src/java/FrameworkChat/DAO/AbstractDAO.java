/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkChat.DAO;

import DAO.UsuarioTxtDAO;
import DAO.UsuarioXmlDAO;
import FrameworkChat.Exception.OperacaoArquivoException;
import FrameworkChat.Exception.UsuarioJaCadastradoException;
import FrameworkChat.Exception.UsuarioNaoCadastradoException;
import FrameworkChat.Modelo.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Guilherme
 */
public abstract class AbstractDAO {
    
    protected String arquivo;
    
    public static AbstractDAO obterDAO(String op){
        
        op = op.toLowerCase();
        
        if(op.equals("txt"))
            return new UsuarioTxtDAO();
        else if(op.equals("xml"))
            return new UsuarioXmlDAO();
        else
            return null;
        
    }
    
    public abstract void salvarUsuario(Usuario u) throws UsuarioJaCadastradoException, OperacaoArquivoException;
    public abstract void excluirUsuario(String login, String senha) throws UsuarioNaoCadastradoException, OperacaoArquivoException;
    public abstract void alterarUsuario(String login, String senhaAntiga, Usuario novoUsuario) throws OperacaoArquivoException;
    public abstract Usuario consultarUsuario(String login, String senha);
    public abstract Usuario consultarUsuario(String login);
    public abstract ArrayList<Usuario> obterCadastro();

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
    
}
