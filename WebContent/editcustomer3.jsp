<%@page import="bank.BankClient"%>
<%
    String custId = request.getParameter("custId");
    String firstName = request.getParameter("fname");
    String lastName = request.getParameter("lname");

    BankClient client = new BankClient();

    int returnValue = client.updateCustomer(custId, firstName, lastName);

    if (returnValue == 0) {
        response.sendRedirect("editcustomer1.jsp?displayMessage=Customer Updated Successfully!");
    }else{
        response.sendRedirect("editcustomer1.jsp?displayMessage=Error: Customer Not Updated!");
    }

%>