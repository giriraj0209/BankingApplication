<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Banking Application</title>
        <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" media="screen" />
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="script.js"></script>
        <style>
            .card {background: #FFF none repeat scroll 0% 0%; box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.3); margin-bottom: 30px; }
            
        </style>
        <link href="css/simple-sidebar.css" rel="stylesheet"  type="text/css" media="screen">

    </head>
    <body>
         <%
            if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
                response.sendRedirect("index.jsp?displayMessage=You are not logged in!");
                return;
            }
        %> 
<div id="wrapper">
<%@ include file="sidebar/sidebar.tpl" %>
<div id="page-content-wrapper">
    <center>
    <h3>Welcome to Faultless Vault System</h3>
        </center>
</div>
       </div>
    </body>
</html>
