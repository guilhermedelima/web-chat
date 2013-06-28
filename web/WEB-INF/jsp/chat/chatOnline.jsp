<%-- 
    Document   : chatOnline
    Created on : 01/03/2013, 02:41:11
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
            
            
            function loadMessages(){
               
               var timeout;
               $.getJSON('<c:url value="/chat/ajax/chatOnline?remetente=${loginRemetente}&destinatario=${loginDestinatario}"/>', function(json){

                   if(json.chat.mensagens.length > 0){

                       $('#divChat').empty();
                       
                       var content = '<p>Chat Online</p><br></br>';
                       content += "<table>";
                       content +=  '<thead>'+
                                    '<th style="border: 1px solid #000;">Mensagens - Chat Online</th>'+
                                   '</thead>';
                           
                       for(var i in json.chat.mensagens){
                           
                            var chatTemp = json.chat.mensagens[i];
                            content += '<tr><td style=\"border: 1px solid #000;\">' +chatTemp.remetente+'</td>';
                            content += '<td style=\"border: 1px solid #000;\">'+chatTemp.texto+ '</td>';
                            content += '</tr>';
                       }

                       content += "</table>";
                       $('#divChat').append(content);
                   }
                   
                   timeout = setTimeout(loadMessages, 1000)
               });
               
            }
            
            loadMessages();
        </script>
        <script language="javascript" type="text/javascript" >
            
            $(document).ready(function(){
                $("#enviar").click(function(){
                    
                    if ( $("#mensagem").val().length > 0){
                        
                        var msg = $("#mensagem").val();
                        var remetente = $("#remetente").val();
                        var destinatario = $("#destinatario").val();
                        
                        $.ajax({
                            type: 'post',
                            data: 'msg='+msg+'&remetente='+remetente+'&destinatario='+destinatario,
                            url: '/WebChat/chat/ajax/atualizarChat'
                        })
                    }
                });
            });
            
        </script>
    </head>
    <body>
        <h1>Chat Online</h1>
        <div id="divChat" >
        </div>
        <br></br>
        <input id ="mensagem" name="mensagem" type="text" />     <input id="enviar" type="button" value="Enviar" />
        <input id ="remetente" name="remetente" type="hidden" value="${loginRemetente}"/>
        <input id ="destinatario" name="destinatario" type="hidden" value="${loginDestinatario}"/>
        <br></br>
        <input type="button" value="Sair do chat" onClick="document.location.href='/WebChat/chat/efetuarLogout'"/>
    </body>
</html>
