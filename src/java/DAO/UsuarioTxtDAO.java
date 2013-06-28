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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Guilherme
 */
public class UsuarioTxtDAO extends AbstractDAO{
    
    public UsuarioTxtDAO(){
        this.arquivo = "Usuario.txt";
    }
    
    @Override
    public void salvarUsuario(Usuario u) throws UsuarioJaCadastradoException, OperacaoArquivoException{

            ArrayList<Usuario> cadastroAtual = obterCadastro();
            
            for(Usuario temp : cadastroAtual){
                if(u.getLogin().equals(temp.getLogin()))
                    throw new UsuarioJaCadastradoException();
                 }
            
            cadastroAtual.add(u);
            atualizarCadastro(cadastroAtual);
    }
    
    @Override
    public void excluirUsuario(String login, String senha) throws UsuarioNaoCadastradoException, OperacaoArquivoException{
        
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
    public void alterarUsuario(String login, String senhaAntiga, Usuario novoUsuario) throws OperacaoArquivoException{
        
        try{
            excluirUsuario(login, senhaAntiga);
            salvarUsuario(novoUsuario);
        }catch(UsuarioJaCadastradoException e){
            
        }catch(UsuarioNaoCadastradoException e){
            
        }
    }
    
    @Override
    public Usuario consultarUsuario(String login, String senha){
        
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
    public ArrayList<Usuario> obterCadastro(){
        
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        
        try{

            FileInputStream arquivoLeitura = new FileInputStream(getArquivo());
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);

            usuarios = (ArrayList<Usuario>) objLeitura.readObject();
            
            objLeitura.close();
            arquivoLeitura.close();
            
            return usuarios;
        
        }catch(Exception ioAndclassNotFound){
            return usuarios;
        }
        
    }
    
    public void atualizarCadastro(ArrayList<Usuario> cadastro) throws OperacaoArquivoException{
        
        try{
            FileOutputStream file = new FileOutputStream(getArquivo(), false);
            ObjectOutputStream obj  = new ObjectOutputStream(file);

            obj.writeObject(cadastro);
            obj.close();
            
        }catch (IOException ex) {
            throw new OperacaoArquivoException();
        }
    }
    
    public void limparCadastro() throws OperacaoArquivoException{
        
        try{
            
            FileWriter file = new FileWriter(getArquivo(), false);
            file.close();
            
        }catch(IOException e){
            throw new OperacaoArquivoException();
        }
        
    }

}
