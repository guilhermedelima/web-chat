<%-- 
    Document   : cadastroIndex
    Created on : 19/02/2013, 09:38:42
    Author     : guilherme
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Chat</title>
    </head>
    <body>
        <h1>Pagina de Cadastro de Usuários</h1>
        <div id ="botoes">
            <input id="incluir" type="button" value="Fazer uma nova conta" onClick="document.location.href='/FrameworkDAS/cadastro/incluir'"/><br></br>
            <input id="alterar" type="button" name ="operacao" value="Alterar sua conta" onClick="document.location.href='/FrameworkDAS/cadastro/autenticar?operacao=alterar'"/><br></br>
            <input id="excluir" type="button" name ="operacao" value="Excluir sua conta" onClick="document.location.href='/FrameworkDAS/cadastro/autenticar?operacao=excluir'"/><br></br>
            <input id="consultar" type="button" value="Visualizar Contas" onClick="document.location.href='/FrameworkDAS/cadastro/listarCadastro'"/><br></br>
        </div>
        <c:if test="${errors != null}">
            <div class="error" style="color: red"> 
                <c:forEach var="error" items="${errors}">
                    &bull; ${error.message}
                </c:forEach>
            </div>
        </c:if>
    </body>
</html>
