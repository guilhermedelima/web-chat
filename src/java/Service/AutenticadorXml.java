/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import FrameworkChat.Serviço.Autenticador;
import FrameworkChat.DAO.AbstractDAO;

/**
 *
 * @author Guilherme
 */
public class AutenticadorXml extends Autenticador{

    public AutenticadorXml(String op) {
        this.dao = AbstractDAO.obterDAO(op);
    }
    

    
    
    
}
