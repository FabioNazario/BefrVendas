<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@tag description="mensagens" pageEncoding="UTF-8"%>

<%@attribute name="pagina"%>

<c:if test="${not empty msgErro}">
    <div class="alert alert-danger alert-dismissible" role="alert">
        ${msgErro}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>
<c:if test="${not empty msgAlerta}">
    <div class="alert alert-warning alert-dismissible" role="alert">
        ${msgAlerta}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>
<c:if test="${not empty msgSucesso}">
    <div class="alert alert-success alert-dismissible" role="alert">
        ${msgSucesso}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>