/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TesteFramework.DAO;

import FrameworkChat.DAO.AbstractDAO;
import FrameworkChat.Exception.UsuarioJaCadastradoException;
import FrameworkChat.Exception.UsuarioNaoCadastradoException;
import FrameworkChat.Modelo.Usuario;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author guilherme
 */
public class TesteUsuarioXmlDAO {
    
    private Usuario u, u2, u3;
    private AbstractDAO uDAO;
    
    @Before
    public void iniciarUsuario(){
        u = new Usuario("Joao", "abc", "Joao MM", 2);
        u2 = new Usuario("Pedro", "123", "PPPPPPPPP", 4);
        u3 = new Usuario("Jose", "321", "Jose MM", 5);
        uDAO = AbstractDAO.obterDAO("xml");
    }
    
    @Test
    public void testeDeArmazenamentoDeUsuario(){
        
        uDAO.setArquivo("UsuarioTeste1.txt");
        try{
            uDAO.salvarUsuario(u);
            uDAO.salvarUsuario(u2);
        }catch(Exception e){
            Assert.assertTrue(false);
        }

    }

    @Test
    public void testeDeSalvarUsuarioRepetido(){
        
        uDAO.setArquivo("UsuarioTeste2.txt");
        
        try{
            uDAO.salvarUsuario(u);
            uDAO.salvarUsuario(u);
            Assert.assertTrue(false);
        }catch(UsuarioJaCadastradoException e){
            Assert.assertTrue(true);
        }catch(Exception e2){
            Assert.assertTrue(false);
        }
        
        try{
            uDAO.salvarUsuario(u2);
        }catch(Exception e3){
            Assert.assertTrue(false);
        }
        
    }
    
    @Test
    public void excluirUsuario(){
        
        uDAO.setArquivo("UsuarioTeste3.txt");
        
        try{
            uDAO.salvarUsuario(u);
            uDAO.salvarUsuario(u2);
            uDAO.excluirUsuario(u2.getLogin(), u2.getSenha());
        }catch(Exception e){
            Assert.assertTrue(false);
        }
        
        try{
            uDAO.excluirUsuario(u3.getLogin(), u3.getNome());
            Assert.assertTrue(false);
        }catch(UsuarioNaoCadastradoException e){
            Assert.assertTrue(true);
        }catch(Exception e){
            Assert.assertTrue(false);
        }
    }
    
    @Test
    public void testeDeconsultaUsuario(){
        
        uDAO.setArquivo("UsuarioTeste4.txt");
        
        try{
            uDAO.salvarUsuario(u);
            uDAO.salvarUsuario(u2);
            uDAO.salvarUsuario(u3);
        }catch(Exception e){
            Assert.assertTrue(false);
        }
        
        Usuario temp = uDAO.consultarUsuario("Pedro", "123");
        Assert.assertNotNull(temp);
        Assert.assertEquals("Pedro", temp.getLogin());
        Assert.assertEquals("123", temp.getSenha());
        
    }
    
    @Test
    public void testeDeAlteracaoDeUsuario(){
        
        uDAO.setArquivo("UsuarioTeste5.txt");
        try{
            uDAO.salvarUsuario(u);
            uDAO.salvarUsuario(u2);
            uDAO.salvarUsuario(u3);
        }catch(Exception e){
            Assert.assertTrue(false);
        }
        
        Usuario novo = new Usuario(u2.getLogin(), "NOVA", "NOVA", 88);
        
        try{
            uDAO.alterarUsuario(u2.getLogin(), u2.getSenha(), novo);
        }catch(Exception e){
            Assert.assertTrue(false);
        }
        Usuario teste = uDAO.consultarUsuario("Pedro", "NOVA");
        
        Assert.assertNotNull(teste);
        Assert.assertEquals("NOVA", teste.getSenha());
        Assert.assertEquals("NOVA", teste.getNome());
        Assert.assertEquals(88, teste.getIdade());
        
    }
     
    @After
    public void excluirTxt(){
        try{
            FileReader f = new FileReader(uDAO.getArquivo());
            
        }catch(IOException e){
            Assert.assertTrue(false);
        }
        
        File file = new File(uDAO.getArquivo());
        file.delete();
        
        /*
        System.gc();
        if(!file.delete())
            Assert.assertTrue(false);
         * Para Windows
         */
    }
}
