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
                $("#excluirCadastro").click(function(){
                    if(confirm("Você deseja mesmo excluir seu cadastro?")){
                        $("#formulario").attr("action", "/WebChat/cadastro/excluirUsuario");
                        $("#formulario").submit();
                    }else
                        return false;
                    
                });
            });
        </script>
        <script language="javascript" type="text/javascript" >
            
            $(document).ready(function(){
                $("#alterarCadastro").click(function(){
                    
                    if( $("#loginUsuario").val() != $("#loginUsuarioBackup").val()){
                        alert("O campo de login nao pode ser alterado");
                        return false;
                    }
                    
                    if ( $("#nomeUsuario").val().length < 3) {
                        alert("O campo nome devem ter pelo menos 3 caracteres");
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
                    
                    if(confirm("Você deseja mesmo alterar seu cadastro?")){
                        $("#formulario").attr("action", "/WebChat/cadastro/alterarUsuario");
                        $("#formulario").submit();
                    }else
                        return false;
                    
                });
            });
        </script>
    </head>
    <body>
        <h1>Cadastro de Usuario</h1>
        
        <form id="formulario" action="" method="post" >
            <c:if test="${operacao == 'excluir'}">
                LOGIN: ${loginUsuario}<br></br>
                <input id ="loginUsuario" name="login" type="hidden" value="${loginUsuario}"/>
                SENHA: ${senhaUsuario}<br></br>
                <input name="senha" type="hidden" value="${senhaUsuario}"/>
                NOME: ${nomeUsuario}<br></br>
                IDADE: ${idadeUsuario}<br></br>
                <input id="excluirCadastro" type="button" value="Excluir cadastro do sistema" /><br></br>
            </c:if>
            <c:if test="${operacao == 'alterar'}">
                LOGIN: <input id ="loginUsuario" name="login" type="text" value="${loginUsuario}"/><br></br>
                       <input id ="loginUsuarioBackup" type="hidden" value="${loginUsuario}"/>
                SENHA: <input id ="senhaUsuario" name="novaSenha" type="password" value="${senhaUsuario}"/><br></br>
                       <input name="senha" type="hidden" value="${senhaUsuario}"/>
                NOME: <input id ="nomeUsuario" name="nome" type="text" value="${nomeUsuario}"/><br></br>
                IDADE: <input id ="idadeUsuario" name="idade" type="text" value="${idadeUsuario}"/><br></br>
                <input id="alterarCadastro" type="button" value="Alterar cadastro" />
            </c:if>
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
