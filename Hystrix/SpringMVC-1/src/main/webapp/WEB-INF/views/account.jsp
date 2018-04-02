<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="com.example.demo.Account" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Details</title>
</head>
<body>
  <h1>Account Details</h1>
  <hr>

 
   <!-- provide an html table start tag -->
   
   
     <!--provide an html table start tag -->
   <table style="border: 1px solid;">
    <!-- create cells of row -->
       <td>Account ID</td>
       <td>Account Number</td>
   <!-- iterate over the collection using forEach loop -->
   <c:forEach var="account" items="${accounts}">
       <!-- create an html table row -->
       <tr>
       <!-- create cells of row -->
       <td>${account.id}</td>
       <td>${account.number}</td>
       <!-- close row -->
       </tr>
       <!-- close the loop -->
   </c:forEach>
   <!-- close table -->
   </table>

   
</body>
</html>