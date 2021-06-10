<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<c:if test="${empty produtos && empty mensagem}">
    <jsp:forward page="../produtos/listar" /> 
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BEFR-Vendas</title>
        <!-- --------------------------------------------------------------- -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap.min.js"></script>
        <!-- --------------------------------------------------------------- -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap.min.css"/>
        <!-- --------------------------------------------------------------- -->
        <script type="text/javascript">
            $(document).ready(function () {
                $('#produtos').DataTable({
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/1.10.24/i18n/Portuguese-Brasil.json"
                    }
                });
            });
        </script>
    </head>
    <body>
    <nav class="navbar navbar-inverse navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="../index.jsp">BEFR Vendas</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="../produtos/index.jsp">Fornecedores</a></li>
                    <li><a href="../contatos/index.jsp">Contatos</a></li>
                    <li class="active"><a href="index.jsp">Produtos</a></li>
                    <li><a href="../cotacoes/index.jsp">Cotações</a></li>
                </ul>
            </div>            
        </div>
    </nav>
    <div class="container">
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
                    <input type="text" class="form-control" name="descricao" 
                           placeholder="Digite a Descrição" value="${produto.descricao}">
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
        <c:choose>
            <c:when test="${not empty produtos}">
                <div style="width: 600px">
                    <table class="table" id="produtos">
                        <thead>
                            <tr>
                                <th>ID</th><th>Razão Social</th><th>Nome Fantasia</th><th>CNPJ</th><th>&nbsp;</th><th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="prod" items="${produtos}">
                                <tr>
                                    <td>${prod.id}</td>
                                    <td>${prod.nome}</td>
                                    <td>${prod.descricao}</td>
                                    <td>${prod.marca}</td>
                                    <td><a href="editar?id=${prod.id}"><img src="../imagens/edit.png" /></a></td>
                                    <td><a href="excluir?id=${prod.id}"><img src="../imagens/edit_remove.png" /></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:when test="${not empty mensagem}">
                <h3 style="color: blue">${mensagem}</h3>
            </c:when>
        </c:choose>
    </div>
</body>
</html>
