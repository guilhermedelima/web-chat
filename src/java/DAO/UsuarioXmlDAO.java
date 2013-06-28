/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import FrameworkChat.DAO.AbstractDAO;
import FrameworkChat.Exception.OperacaoArquivoException;
import FrameworkChat.Exception.UsuarioJaCadastradoException;
import FrameworkChat.Exception.UsuarioNaoCadastradoException;
import FrameworkChat.Modelo.Usuario;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.JDomDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class UsuarioXmlDAO extends AbstractDAO{

    public UsuarioXmlDAO() {
        this.arquivo = "Usuarios.xml";
    }

    
    
    
    @Override
    public void salvarUsuario(Usuario u) throws UsuarioJaCadastradoException, OperacaoArquivoException {
        
        ArrayList<Usuario> cadastroAtual = obterCadastro();
        
        for(Usuario temp : cadastroAtual){
            if(u.getLogin().equals(temp.getLogin()))
                throw new UsuarioJaCadastradoException();
        }
        
        cadastroAtual.add(u);
        atualizarCadastro(cadastroAtual);
    }

    @Override
    public void excluirUsuario(String login, String senha) throws UsuarioNaoCadastradoException, OperacaoArquivoException {
        ArrayList<Usuario> cadastroAtual = obterCadastro();
        Iterator i = cadastroAtual.iterator();
        
        while(i.hasNext()){
            Usuario temp = (Usuario) i.next();

            if(login.equals(temp.getLogin()) && senha.equals(temp.getSenha())){
                i.remove();
                atualizarCadastro(cadastroAtual);
                return;
            }
        }
        throw new UsuarioNaoCadastradoException();
    }

    @Override
    public void alterarUsuario(String login, String senhaAntiga, Usuario novoUsuario) throws OperacaoArquivoException {
        try{
            excluirUsuario(login, senhaAntiga);
            salvarUsuario(novoUsuario);
        }catch(UsuarioJaCadastradoException e){
            
        }catch(UsuarioNaoCadastradoException e){
            
        }
    }

    @Override
    public Usuario consultarUsuario(String login, String senha) {
        ArrayList<Usuario> cadastroAtual = obterCadastro();
        
        for(Usuario temp : cadastroAtual){
            if(login.equals(temp.getLogin()) && senha.equals(temp.getSenha()))
                return temp;
        }
        
        return null;
    }
    
    @Override
    public Usuario consultarUsuario(String login) {
        ArrayList<Usuario> cadastroAtual = obterCadastro();
        
        for(Usuario temp : cadastroAtual){
            if(login.equals(temp.getLogin()))
                return temp;
        }
        
        return null;
    }

    @Override
    public ArrayList<Usuario> obterCadastro() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("Cadastro", List.class);
        xstream.alias("UsuarioChat", Usuario.class);
        
        try{

            FileInputStream arquivoLeitura = new FileInputStream(getArquivo());
            usuarios = (ArrayList) xstream.fromXML(arquivoLeitura);
            
            arquivoLeitura.close();
            
            return usuarios;
            
        }catch(Exception ioAndclassNotFound){
            return usuarios;
        }
    }
    
    public void atualizarCadastro(ArrayList<Usuario> cadastro) throws OperacaoArquivoException{
        
        try{
            
            XStream xstream = new XStream();
            xstream.alias("Cadastro", List.class);
            xstream.alias("UsuarioChat", Usuario.class);
            
            FileOutputStream file = new FileOutputStream(getArquivo(), false);
            file.write(xstream.toXML(cadastro).getBytes());
            
            file.close();
            
        }catch (IOException ex) {
            throw new OperacaoArquivoException();
        }
        
    }

    }
