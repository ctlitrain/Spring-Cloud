<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="com.example.demo.Account" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Details</title>
</head>
<body>
  <h1>Customer Details</h1>
  <hr>
  <!-- provide an html table start tag -->
  
       <!--provide an html table start tag -->
   <table style="border: 1px solid;">
    <!-- create cells of row -->
       <td>ID</td>
       <td>  Customer Name </td>
        <td>Customer Type</td>
       
   <!-- iterate over the collection using forEach loop --> 
 
       <!-- create an html table row -->
       <tr>
       <!-- create cells of row -->
       <td bgcolor="green">${customer.id}</td>
       <td bgcolor="green">${customer.name}</td>
       <td bgcolor="green">${customer.type}</td>
       <!-- close row -->
       </tr>
       </table>
       <table>
       <td/>    
       <td>Account ID</td>
       <td>Account Number</td>
       <c:forEach var="account" items="${customer.accounts}">
     
        <tr>
        <td/>   
        <td>${account.id}</td>
       <td>${account.number}</td>
       </tr>
        </c:forEach>
      
       <!-- close the loop -->  
   <!-- close table -->
   </table>

   
</body>
</html>