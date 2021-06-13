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
        <tags:navibar pagina="produtos"/>

        <div class="container">
            <c:if test="${empty produtos && empty msgAlerta}">
                <jsp:forward page="../produtos/listar" /> 
            </c:if>
            <tags:mensagens/>
            <form class="form-horizontal" action="salvar" method="post">
                <input type="hidden" name="id" value="${produto.id}" />
                <div class="form-group">
                    <label class="control-label col-sm-2" for="nome">Nome:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="nome" 
                               placeholder="Digite o nome do produto" value="${produto.nome}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="descricao">Descrição:</label>
                    <div class="col-sm-10">
                        <textarea type="text" class="form-control" name="descricao" rows="5"
                               placeholder="Digite a Descrição" value="${produto.descricao}"/></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="marca">Marca:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="marca" 
                               placeholder="Digite a Marca" value="${produto.marca}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">${produto.id != null?'Atualizar':'Salvar'}</button>
                    </div>
                </div>
            </form>
            <c:if test="${not empty produtos}">
                <div style="width: 100%">
                    <table class="table" id="data-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>Descrição</th>
                                <th>Marca</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="prod" items="${produtos}">
                                <tr>
                                    <td class="align-middle">${prod.id}</td>
                                    <td class="align-middle">${prod.nome}</td>
                                    <td class="align-middle">${prod.descricao}</td>
                                    <td class="align-middle">${prod.marca}</td>
                                    <td class="align-middle"><a href="editar?id=${prod.id}"><img src="../imagens/edit_black_24dp.svg" /></a></td>
                                    <td class="align-middle"><a href="excluir?id=${prod.id}"><img src="../imagens/delete_black_24dp.svg" /></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </body>
</html>
