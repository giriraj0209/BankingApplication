<%
    String firstName = request.getParameter("fname");
    String lastName = request.getParameter("lname");

    bank.BankClient client = new bank.BankClient();

    int returnValue = client.createCustomer(firstName, lastName);

    if (returnValue == 0) {
        response.sendRedirect("addcustomer1.jsp?displayMessage=Customer Created Successfully!");
    }else{
        response.sendRedirect("addcustomer1.jsp?displayMessage=Error: Customer Not Created!");
    }

%>
