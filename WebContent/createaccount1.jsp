<%@page import="java.util.ArrayList"%>
<%@page import="customer.Customer"%>
<%@page import="bank.BankClient"%>
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
    <form method="post" action="createaccount2.jsp">
                                            <center>
                                                <%
                                                    BankClient bankClient = new BankClient();
                                                    ArrayList<Customer> customers = bankClient.findCustomers();

                                                    String displayMessage = request.getParameter("displayMessage");
                                                    if (displayMessage != null && displayMessage.length() > 0) {
                                                        out.println("<h3>" + displayMessage + "</h3>");
                                                    }
                                                %>

<div class="col-md-8" style="float: none">
<h3>Create Account</h3>
<div class="card"> 
<div style="width:90%">
<br>
<div class="form-group">
<label for="selc">Select Customer:</label>
<select class="form-control" id="selc" name="custId">
<%
                                                                        if (customers != null) {
                                                                            for (int i = 0; i < customers.size(); i++) {
                                                                                Customer customer = customers.get(i);
                                                                                out.println("<option value=" + customer.getCustId() + ">" + customer.getFirstName() + " " + customer.getLastName() + "</option>");
                                                                            }
                                                                        }
%>
</select>
<br>
<input type="submit" value="Submit" class="btn btn-primary" style="margin-bottom: 10px;"/>
</div>
</div>
</div>
</div>
</center>
</form>
</div>
</div>
</body>
</html>