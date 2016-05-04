<%@page import="account.Account"%>
<%@page import="transaction.Transaction"%>
<%@page import="java.util.ArrayList"%>
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
    <%
        BankClient client = new BankClient();

        ArrayList<Account> accounts = client.accountDetails();
    %>
    <body>
        <% if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
                response.sendRedirect("index.jsp?displayMessage=You are not logged in!");
                return;
            }
        %> 
        <div id="wrapper">
<%@ include file="sidebar/sidebar.tpl" %>
<div id="page-content-wrapper">
 <form method="post" action="addcustomer2.jsp">
                                            <center>
                                                
                                                <%
                                                    String displayMessage = request.getParameter("displayMessage");
                                                    if (displayMessage != null && displayMessage.length() > 0) {
                                                        out.println("<h3>" + displayMessage + "</h3>");
                                                    }
                                                %>
                                                <h2>Balance Sheet</h2>
                                                <table class="table table-striped table-bordered" >

                                                    <input name="radiobutton" type="hidden" value="5555">
                                                    <tr align=center bgcolor="#FFFF00">
                                                        <td><span class="style3">S.No</span></td>
                                                        <td><span class="style3">Customer Name</span></td>
                                                        <td><span class="style3">Account Status</span></td>
                                                        <td><span class="style3">Balance</span></td>
                                                    </tr>
                                                    <%
                                                        for (int i = 0; i < accounts.size(); i++) {
                                                            Account account = accounts.get(i);
                                                    %>
                                                    <tr>
                                                        <td align=center><span class="style3"><%= i + 1%></span></td>
                                                        <td align=center><span class="style3"><%= account.getFirstName() + " " + account.getLastName()%></span></td>
                                                        <td align=center><span class="style3"><%= account.getActive()%></span></td>
                                                        <td align=center><span class="style3"><%= account.getBalance()%></span></td>
                                                    </tr>
                                                    <%
                                                        }
                                                    %>

                                                </table>
                                            </center>
                                        </form>
                                    </div>
                                </div>
       </body>
</html>
