<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tags" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <tags:head/>
    </head>
    <body>
        <tags:navibar pagina="cotacoes"/>
        <div class="container">
            <c:if test="${empty cotacoes && empty msgAlerta}">
                <jsp:forward page="../cotacoes/listar" /> 
            </c:if>
            <tags:mensagens/>
            <form class="form-horizontal" action="salvar" method="post" onsubmit="ajustaValorMoeda()">
                <input type="hidden" name="id" value="${cotacao.id}" />
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
                    <label class="control-label col-sm-2" for="nome">Produto:</label>
                    <div class="col-sm-10">
                        <select class="form-control form-control-lg"  name="prodId">
                            <option value="0">Selecione...</option>
                            <c:forEach var="prod" items="${produtos}">
                                <option ${prod.id == produto.id ?"selected":""} value="${prod.id}">${prod.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="valor">Valor(R$):</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control moeda" name="valor" id="valor"  
                               placeholder="Digite o Valor" value="${cotacao.valor}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">${cotacao.id != null?'Atualizar':'Salvar'}</button>
                    </div>
                </div>
            </form>
            <c:choose>
                <c:when test="${not empty cotacoes}">
                    <div style="width: 100%">
                        <table class="table" id="data-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Data</th>
                                    <th>Fornecedor</th>
                                    <th>Produto</th>
                                    <th>Valor</th>
                                    <th>&nbsp;</th>
                                    <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cota" items="${cotacoes}">
                                    <tr>
                                        <td >${cota.id}</td>
                                        <td ><fmt:formatDate value= "${cota.data}" 
                                                        pattern="dd/MM/yyyy HH:mm:ss"/>
                                        </td>
                                        <td >${cota.fornecedor.nomeFantasia}</td>
                                        <td >${cota.produto.nome}</td>
                                        <fmt:setLocale value="pt_BR" scope="session"/>
                                        <td ><fmt:formatNumber value= "${cota.valor}" 
                                                          type="currency"/>
                                        </td>
                                        <td ><a href="editar?id=${cota.id}"><img src="../imagens/edit_black_24dp.svg" /></a></td>
                                        <td ><a href="excluir?id=${cota.id}" onclick="return confirm('Tem certeza que deseja excluir a cotação ${cota.id}?' )"><img src="../imagens/delete_black_24dp.svg" /></a></td>
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
        function ajustaValorMoeda() {
            value = document.getElementById("valor").value;
            value = value.replaceAll(".","").replaceAll(",",".")
            document.getElementById("valor").value = value;
        }
        
        $(document).ready(function()
            {
                 $('.moeda').mask('#.##0,00', {reverse: true});
            });
    </script>
</html>
