<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@tag description="navibar" pageEncoding="UTF-8"%>

<%@attribute name="pagina"%>

<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>

<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${path}/index.jsp">BEFR Vendas</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li ${pagina == "fornecedores"?"class=active":""}>
                    <a href="${path}/fornecedores/index.jsp">Fornecedores</a></li>
                <li ${pagina == "contatos"?"class=active":""}>
                    <a href="${path}/contatos/index.jsp">Contatos</a></li>
                <li ${pagina == "produtos"?"class=active":""}>
                    <a href="${path}/produtos/index.jsp">Produtos</a></li>
                <li ${pagina == "cotacoes"?"class=active":""}>
                    <a href="${path}/cotacoes/index.jsp">Cotações</a></li>
            </ul>
        </div>            
    </div>
</nav>