<%-- 
    Document   : inicioChat
    Created on : 27/02/2013, 22:37:23
    Author     : guilherme
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Chat</title>
        <script type="text/javascript" src="<c:url value="/js/jquery-1.7.2.js"/>"></script>
        <script language="javascript" type="text/javascript" >
            
            var timeout;
            function loadUsuariosOnline(){
               
               $.getJSON('<c:url value="/chat/ajax/usuarios"/>', function(json){
                   
                   if(json.list.length > 0){

                       $('#divTabela').empty();
                       
                       var content = "<table>";
                       content +=  '<thead>'+
                                    '<th style="border: 1px solid #000;">Login</th>'+
                                    '<th style="border: 1px solid #000;">Nome</th>'+
                                    '<th style="border: 1px solid #000;">Idade</th>'+
                                   '</thead>';
                       
                       for(var i in json.list){
                           var cliente = json.list[i];
                           
                            content += '<tr><td style=\"border: 1px solid #000;\">' +cliente.login+ '</td>';
                            content += '<td style=\"border: 1px solid #000;\">' +cliente.nome+ '</td>';
                            content += '<td style=\"border: 1px solid #000;\">' +cliente.idade+ '</td>';
                            content += '<td style=\"border: 1px solid #000;\"> <input type=\"button\" value=\"Iniciar nova conversa\" onClick=\"document.location.href=\'/FrameworkDAS/chat/abrirChat?destinatario='+cliente.login+'\'\" /></td></tr>';
                       }
                       
                       content += "</table>";
                       $('#divTabela').append(content);
                   }
                   
                   
                   timeout = setTimeout(loadUsuariosOnline, 5000)
               });
               
            }
            
            loadUsuariosOnline();
        </script>
        <script language="javascript" type="text/javascript" >
            
            var timeout;
            function loadChatRequest(){
               
               $.getJSON('<c:url value="/chat/ajax/solicitacoes?loginSessao=${login}"/>', function(json){
                   
                   if(json.list.length > 0){

                       $('#divChat').empty();
                       
                       var content = '<p>Solicitações de Chat</p>';
                       content += "<table>";
                       content +=  '<thead>'+
                                    '<th style="border: 1px solid #000;">Nome User1</th>'+
                                   '</thead>';
                       
                       for(var i in json.list){
                           var chat = json.list[i];
                           
                            content += '<tr><td style=\"border: 1px solid #000;\">' +chat.remetente.nome+ '</td>';
                            content += '<td style=\"border: 1px solid #000;\"> <input type=\"button\" value=\"Aceitar\" onClick=\"document.location.href=\'/FrameworkDAS/chat/aceitarChat?remetente='+chat.remetente.login+'\'\" /></td></tr>';
                       }
                       
                       content += "</table>";
                       $('#divChat').append(content);
                   }
                   
                   
                   timeout = setTimeout(loadChatRequest, 5000)
               });
               
            }
            
            loadChatRequest();
        </script>
    </head>
    <body>
        <h1>Bem vindo, ${nome}</h1>
        <p>Usuarios Online</p>
        <div id="divTabela" >
        </div>
        <div id="divChat" >
        </div>
        <input type="button" value="Efetuar Logout" onClick="document.location.href='/FrameworkDAS/chat/efetuarLogout'"/>
        
    </body>
</html>
