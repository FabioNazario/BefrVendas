<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <tags:head/>
    </head>
    <body>
    <nav class="navbar navbar-inverse navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="../index.jsp">BEFR Vendas</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="../fornecedores/index.jsp">Fornecedores</a></li>
                    <li class="active"><a href="index.jsp">Contatos</a></li>
                    <li><a href="../produtos/index.jsp">Produtos</a></li>
                    <li><a href="../cotacoes/index.jsp">Cotações</a></li>
                </ul>
            </div>            
        </div>
    </nav>
    <div class="container">
        <c:if test="${empty contatos && empty msgAlerta && empty msgErro && empty msgSucesso}">
            <jsp:forward page="listar" />
        </c:if>
        <tags:mensagens/>
        <form class="form-horizontal" action="salvar" method="post">
            <input type="hidden" name="id" value="${contato.id}" />
            <div class="form-group">
                <label class="control-label col-sm-2" for="nome">Fornecedor:</label>
                <div class="col-sm-10">
                    <select name="fornId">
                        <option value="0">Selecione...</option>
                        <c:forEach var="forn" items="${fornecedores}">
                            <option ${forn.id == fornecedor.id ?"selected":""} value="${forn.id}">${forn.nomeFantasia}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="nome">Nome:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="nome" 
                           placeholder="Digite o Nome" value="${contato.nome}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Email:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="email" 
                           placeholder="Digite o Email" value="${contato.email}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="fone">Telefone:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="fone" 
                           placeholder="Digite o Telefone" value="${contato.fone}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">${contato.id != null?'Atualizar':'Salvar'}</button>
                </div>
            </div>
        </form>
        <c:choose>
            <c:when test="${not empty contatos}">
                <div style="width: 600px">
                    <table class="table" id="data-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>Email</th>
                                <th>Telefone</th>
                                <th>Fornecedor</th>
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="contato" items="${contatos}">
                                <tr>
                                    <td>${contato.id}</td>
                                    <td>${contato.nome}</td>
                                    <td>${contato.email}</td>
                                    <td>${contato.fone}</td>
                                    <td>${contato.fornecedor.nomeFantasia}</td>
                                    <td><a href="editar?id=${contato.id}"><img src="../imagens/edit_black_24dp.svg" /></a></td>
                                    <td><a href="excluir?id=${contato.id}"><img src="../imagens/delete_black_24dp.svg" /></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
        </c:choose>
    </div>
</body>
</html>
