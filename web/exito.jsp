<% 
String mensaje = request.getSession().getAttribute("mensaje").toString();
String proceso= request.getSession().getAttribute("proceso").toString();
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>EXITO</h1>
        <label><%=mensaje%></label>
    </body>
</html>
