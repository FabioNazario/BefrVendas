<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tags" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <tags:head/>
    </head>
    <body>
        <tags:navibar pagina="contatos"/>
    <div class="container">
        <c:if test="${empty contatos && empty msgAlerta}">
            <jsp:forward page="listar" />
        </c:if>
        <tags:mensagens/>
        <form class="form-horizontal" action="salvar" method="post">
            <input type="hidden" name="id" value="${contato.id}" />
            <div class="form-group">
                <label class="control-label col-sm-2" for="nome">Fornecedor:</label>
                
                <div class="col-sm-10">
                    <select class="form-control form-control-lg"  name="fornId">
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
                    <input type="text" class="form-control telefone" name="fone" 
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
                <div style="width: 100%">
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
                                    <td class="align-middle">${contato.id}</td>
                                    <td class="align-middle">${contato.nome}</td>
                                    <td class="align-middle">${contato.email}</td>
                                    <td class="align-middle">${contato.fone}</td>
                                    <td class="align-middle">${contato.fornecedor.nomeFantasia}</td>
                                    <td class="align-middle"><a href="editar?id=${contato.id}"><img src="../imagens/edit_black_24dp.svg" /></a></td>
                                    <td class="align-middle"><a href="excluir?id=${contato.id}"><img src="../imagens/delete_black_24dp.svg" /></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
        </c:choose>
    </div>
</body>
<script>
 
        
        $(document).ready(function()
            {
                 $(".telefone").mask("(00) 00000-0009");
            });
    </script>
</html>
