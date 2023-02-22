<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css"></link>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<link>
<title>Insert title here</title>
</head>
<body>
<%@ include file="./common/header.jspf"%>
	<div class="wrap">
		<div class="box">
			<div class="content">
				<spring:form action="./registerationCheck" method="post">
					<div class="logo-wrap">
						<i class="fa-solid fa-key"></i>
					</div>
					<h1>Welcome!</h1>
					<div class="input-box">
						Enter Username: <spring:input path="userName" type="text"/> <i class="fa-solid fa-user"></i>
						
					</div>
					<div class="input-box">
						Enter Balance: <spring:input path="balance" type="number"  value="0"/> <i
							class="fa-solid fa-lock"></i> 
					</div>
					<div class="input-box">
						<input type="submit" value="Register">
					</div>

				</spring:form>
			</div>
		</div>
	</div>

</body>
</html>