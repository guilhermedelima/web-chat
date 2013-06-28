<%-- 
    Document   : cadastro
    Created on : 17/02/2013, 19:57:22
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
                $("#cadastrar").click(function(){
                    
                    if ( $("#loginUsuario").val().length < 3 || $("#nomeUsuario").val().length < 3) {
                        alert("Os campos de login e nome devem ter pelo menos 3 caracteres cada");
                        return false;
                    }
                    
                    if($("#senhaUsuario").val().length < 6){
                        alert("O campo senha deve conter pelo menos 6 caracteres");
                        return false;
                    }
                    
                    if( isNaN(formulario.idadeUsuario.value)){
                        alert("O campo idade deve conter apenas numeros");
                        return false;
                    }
                        
                    if( formulario.idadeUsuario.value <= 8 || formulario.idadeUsuario.value >= 150){
                        alert("A idade do usuario deve estar entre 8 e 150 anos");
                        return false;
                    }
                        
                    $("#formulario").submit();
                    
                });
            });
            
        </script>
    </head>
    <body>
        <h1>Cadastro de Usuario</h1>
        
        <form id="formulario" action="<c:url value='/cadastro/cadastrarUsuario'/>" method="post" >
            LOGIN: <input id ="loginUsuario" name="login" type="text" value="${loginUsuario}"/><br></br>
            SENHA: <input id ="senhaUsuario" name="senha" type="password" value="${senhaUsuario}"/><br></br>
            NOME: <input id ="nomeUsuario" name="nome" type="text" value="${nomeUsuario}"/><br></br>
            IDADE: <input id ="idadeUsuario" name="idade" type="text" value="${idadeUsuario}"/><br></br>
            <br></br>
            <input id="cadastrar" type="button" value="Efetuar cadastro" /><br></br>
        </form>
        <c:if test="${errors != null}">
            <div class="error" style="color: red"> 
                <c:forEach var="error" items="${errors}">
                    &bull; ${error.message}
                </c:forEach>
            </div>
        </c:if>
    </body>
</html>
