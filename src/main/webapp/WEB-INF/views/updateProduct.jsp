<%@page import="org.jboss.jandex.TypeTarget.Usage"%>
<%@page import="com.te.productmanagementapp.beans.ProductInfoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String msg = (String) request.getAttribute("msg");
ProductInfoBean infoBean = (ProductInfoBean) request.getAttribute("pid");
%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Data</title>
</head>
<body>
	<fieldset>
		<legend>Add Details </legend>
		<form action="./update" method="post">
			<table>
				<tr>
					<td>Product ID</td>
					<td>:</td>
					<td><%=infoBean.getPid()	%></td>
					<td><input type="hidden" name="pid" value=<%=infoBean.getPid()%>></td>
				</tr>
				<tr>
					<td>Product Name</td>
					<td>:</td>
					<td><input type="text" name="name"
						value=<%=infoBean.getPname()%>></td>
				</tr>
				<tr>
					<td>Product mgDate</td>
					<td>:</td>
					<td><input type="date" name="dob"
						value=<%=infoBean.getMgDate()%>></td>
				</tr>
				<tr>
					<td> Password</td>
					<td>:</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Register"></td>
				</tr>
			</table>
		</form>
	</fieldset>

	<%
		if (msg != null && !msg.isEmpty()) {
	%>
	<h1 style="color: magenta;"><%=msg%>
	</h1>
	<%
		}
	%>

</body>
</html>