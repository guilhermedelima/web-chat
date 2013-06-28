/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkChat.Serviço;

import FrameworkChat.DAO.AbstractDAO;
import FrameworkChat.Modelo.Usuario;
import Service.AutenticadorTxt;
import Service.AutenticadorXml;

/**
 *
 * @author Guilherme
 */
public abstract class Autenticador {
    
    protected AbstractDAO dao;
    
    /*
     * Metodo Fábrica
     */
    public static Autenticador obterAutenticador(String autenticador){
        
        String op = autenticador.toLowerCase();
        
        if(op.equals("xml"))
            return new AutenticadorXml(op);
        else if(op.equals("txt"))
            return new AutenticadorTxt(op);
        else
            return null;
        
    }
    
    public boolean autenticarUsuario(String login, String senha) {
        
        Usuario u = dao.consultarUsuario(login, senha);
        
        try{
            if(login.equals(u.getLogin()) && senha.equals(u.getSenha()))
                return true;
            
        }catch(NullPointerException e){
            return false;
        }
        
        return false;
    }


}
