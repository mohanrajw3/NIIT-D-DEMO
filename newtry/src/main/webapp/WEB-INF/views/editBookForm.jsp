<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:url value="/admin/book/editBook" var="url"></c:url> 
	<form:form method="post" action="${url}" commandName="editProdObj">
		<table>
		<tr>
			<td><form:label path="productid">Product Id</form:label></td>
			<td><form:input path="productid" disabled="true"/>
			<form:hidden path="productid" /></td>
        </tr>
        <tr>
        <td><form:label path="ProductName">TITLE</form:label>  </td>
        <td><form:input path="ProductName"></form:input></td>
	    </tr>
	   
	     <tr>
        <td><form:label path="ProductPrice">PRICE</form:label>  </td>
        <td><form:input path="ProductPrice"></form:input></td>
	  
	    <tr>
	    <td colspan="2"><input type="submit" value="EditBook"></td>
	    </tr>
		</table>
	</form:form>

</body>
</html>
