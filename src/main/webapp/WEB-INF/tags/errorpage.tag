<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@attribute name="mensagem"%>
<html>
    <head>
        <tags:head/>
    </head> 
    <body>
        <tags:navibar pagina="home"/>
        <div style="vertical-align: center" class="container">
            <div class="alert alert-danger alert-dismissible" role="alert">
                ${mensagem}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </div>
    </body>
</html>
