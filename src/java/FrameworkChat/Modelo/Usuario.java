/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameworkChat.Modelo;

import java.io.Serializable;

/**
 *
 * @author guilherme
 */
public class Usuario implements Serializable{
    
    String login;
    String senha;
    String nome;
    int idade;

    public Usuario(String login, String senha, String nome, int idade) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.idade = idade;
    }
    
    public Usuario(){ }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    
    
}
