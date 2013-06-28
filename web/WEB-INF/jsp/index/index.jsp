<%-- 
    Document   : index
    Created on : 15/02/2013, 10:33:09
    Author     : Guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WEB CHAT</title>
    </head>
    <body>
        <h1>WEB CHAT</h1>
        <input id="cadastro" type="button" value="Cadastro de Usuário" onClick="document.location.href='/WebChat/cadastro'">
        <br></br>
        <input id="chat" type="button"  value="Entrar no chat"  onClick="document.location.href='/WebChat/chat/login'"/>
        <br></br>
        <c:if test="${errors != null}">
            <div class="error" style="color: red"> 
                <c:forEach var="error" items="${errors}">
                    &bull; ${error.message}
                </c:forEach>
            </div>
        </c:if>
    </body>
</html>
