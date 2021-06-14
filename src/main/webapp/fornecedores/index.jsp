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
        <tags:navibar pagina="fornecedores"/>
        <div class="container">
            <c:if test="${empty fornecedores && empty msgAlerta}">
                <jsp:forward page="listar" />
            </c:if>
            <tags:mensagens/>
            <form class="form-horizontal" action="salvar" method="post">
                <input type="hidden" name="id" value="${fornecedor.id}" />
                <div class="form-group">
                    <label class="control-label col-sm-2" for="razaoSocial">Razão Social:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="razaoSocial" 
                               placeholder="Digite a Razão Social" value="${fornecedor.razaoSocial}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="nomeFantasia">Nome de Fantasia:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="nomeFantasia" 
                               placeholder="Digite Nome de Fantasia" value="${fornecedor.nomeFantasia}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="cnpj">CNPJ:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control cnpj" name="cnpj" 
                               placeholder="Digite o CNPJ" value="${fornecedor.cnpj}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">${fornecedor.id != null?'Atualizar':'Salvar'}</button>
                    </div>
                </div>
            </form>
            <c:choose>
                <c:when test="${not empty fornecedores}">
                    <div style="width: 100%">
                        <table class="table" id="data-table">
                            <thead>
                                <tr>
                                    <th>ID</th><th>Razão Social</th><th>Nome Fantasia</th><th>CNPJ</th><th>&nbsp;</th><th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="forn" items="${fornecedores}">
                                    <tr>
                                        <td >${forn.id}</td>
                                        <td >${forn.razaoSocial}</td>
                                        <td >${forn.nomeFantasia}</td>
                                        <td >${forn.cnpj}</td>
                                        <td ><a href="editar?id=${forn.id}"><img src="../imagens/edit_black_24dp.svg" /></a></td>
                                        <td ><a href="excluir?id=${forn.id}" onclick="return confirm('ATENÇAO:\nAs cotações e contatos ligados a este fornecedor serão excluidos.\n\nTem certeza que deseja excluir o fornecedor ${forn.nomeFantasia}?' )"><img src="../imagens/delete_black_24dp.svg" /></a></td>
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
                 $(".cnpj").mask("99.999.999/9999-99");
            });
    </script>
    
</html>
