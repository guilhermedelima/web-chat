<%-- 
    Document   : cadastroListagem
    Created on : 25/02/2013, 03:41:57
    Author     : Guilherme
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
        <h1>Lista de Usuários do Sistema</h1>
        <table id="tabela"/>
            <thead>
                <th style="border: 1px solid #000;">Login</th>
                <th style="border: 1px solid #000;">Senha</th>
                <th style="border: 1px solid #000;">Nome</th>
                <th style="border: 1px solid #000;">Idade</th>
            </thead>
            <c:forEach var="usuario" items="${lista}">
                <tr>
                    <td style="border: 1px solid #000;">${usuario.login}</td>
                    <td style="border: 1px solid #000;">${usuario.senha}</td>
                    <td style="border: 1px solid #000;">${usuario.nome}</td>
                    <td style="border: 1px solid #000;">${usuario.idade}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
