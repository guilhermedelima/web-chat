/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TesteFramework.DAO;

import FrameworkChat.DAO.AbstractDAO;
import FrameworkChat.Modelo.Usuario;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author guilherme
 */
public class TesteLibXML {
    private Usuario u, u2, u3;
    private AbstractDAO uDAO;
    
    @Before
    public void iniciarUsuario(){
        u = new Usuario("Joao", "abc", "Joao MM", 2);
        u2 = new Usuario("Pedro", "123", "PPPPPPPPP", 4);
        u3 = new Usuario("Jose", "321", "Jose MM", 5);
    }
    
    @Test
    public void testeLibXstream() throws IOException{
        
        ArrayList<Usuario> init = new ArrayList<Usuario>();
        init.add(u2);
        init.add(u);
        
        XStream xstream = new XStream();
        xstream.alias("Cadastro", List.class);
        xstream.alias("UsuarioChat", Usuario.class);
        

        FileOutputStream file = new FileOutputStream("Teste.xml", false);
        file.write(xstream.toXML(init).getBytes());
        
        file.close();
        
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        
        XStream xstream2 = new XStream(new DomDriver());
        xstream2.alias("Cadastro", List.class);
        xstream2.alias("UsuarioChat", Usuario.class);

        FileInputStream arquivoLeitura = new FileInputStream("Teste.xml");
        usuarios = (ArrayList) xstream2.fromXML(arquivoLeitura);
        
        System.out.println(usuarios.size());
        
        arquivoLeitura.close();
        System.out.println(usuarios.get(0).getLogin());
        System.out.println(usuarios.get(1).getLogin());
        
    }
}
