<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@tag description="head" pageEncoding="ISO-8859-1"%>

<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BEFR-Vendas</title>
<!-- --------------------------------------------------------------- -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://igorescobar.github.io/jQuery-Mask-Plugin/js/jquery.mask.min.js"></script>     
<!-- --------------------------------------------------------------- -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap.min.css"/>
<!-- --------------------------------------------------------------- -->
<link href="${path}/css/style.css" rel="stylesheet" type="text/css"/>
<link rel="icon" href="${path}/imagens/favicon.ico">
<!-- --------------------------------------------------------------- -->
<script type="text/javascript">
    $(document).ready(function () {
        $('#data-table').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.24/i18n/Portuguese-Brasil.json"
            }
        });
    });
</script>