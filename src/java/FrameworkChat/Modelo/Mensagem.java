/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkChat.Modelo;

/**
 *
 * @author guilherme
 */
public class Mensagem {
    
    private String remetente;
    private String texto;

    public String getRemetente() {
        return remetente;
    }

    public Mensagem(String nomeRemetente, String texto) {
        this.remetente = nomeRemetente;
        this.texto = texto;
    }
    
    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
}
