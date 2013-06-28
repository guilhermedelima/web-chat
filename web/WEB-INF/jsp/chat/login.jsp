<%-- 
    Document   : AutenticacaoCadastro
    Created on : 24/02/2013, 21:51:38
    Author     : Guilherme
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
            
            $(document).ready(function(){
                $("#autenticar").click(function(){
                    
                    if( $("#loginUsuario").val().length < 3 || $("#senhaUsuario").val().length < 6 ){
                        alert("Os campos de login e senha devem ter no minímo 3 e 6 digitos, respectivamente");
                        return false;
                    }

                    $("#formulario").submit();
                });
            });
            
            
            
        </script>
    </head>
    <body>
        <h1>Login para entrar no Web Chat</h1>
        <form id="formulario" action="<c:url value='/chat/efetuarLogin'/>" method="post" >
            Login: <input id="loginUsuario" name="login" type="text" /><br></br>
            Senha: <input id="senhaUsuario" name="senha" type="password" /><br></br>
            <input id="autenticar" type="button" value="Entrar"/>
        </form>
    </body>
</html>
