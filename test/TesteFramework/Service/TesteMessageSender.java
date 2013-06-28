/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TesteFramework.Service;

import FrameworkChat.Modelo.Usuario;
import FrameworkChat.Serviço.MessageSender;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author guilherme
 */
public class TesteMessageSender {
    
    private MessageSender sender;
    Usuario u,u2,u3,u4;
    
    @Before
    public void incializar(){
        u = new Usuario("Joao", "abc", "Joao MM", 2);
        u2 = new Usuario("Pedro", "123", "PPPPPPPPP", 4);
        u3 = new Usuario("Jose", "321", "Jose MM", 5);
        u3 = new Usuario("Guilherme", "XPTO", "GUI MS", 21);
    }
    
    @Test
    public void testeDeInclusaoDeChat(){
        
    }
    
    
    
    
    
    
    
    
    
    
    
}
