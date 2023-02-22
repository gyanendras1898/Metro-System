<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href='<c:url value="./css/loginStyle.css"></c:url>'></link>
<title>Login</title>
</head>
<body>
	<%@ include file="./common/header.jspf"%>
	<div id="container" class="container sign-in">
		<!-- FORM SECTION -->
		<div class="row">
			<!-- SIGN UP -->
			<div class="col align-items-center flex-col sign-up">
			</div>
			<!-- END SIGN UP -->
			<!-- SIGN IN -->
			<div class="col align-items-center flex-col sign-in">
				<div class="form-wrapper align-items-center">
					<div class="form sign-in">
					<spring:form action="./loginCheck" method="post">
						<div class="input-group">
							<i class='bx bxs-user'></i> <spring:input path="id" type="text"
								placeholder="User Id" />
						</div>
						<button>Sign in</button>
						</spring:form>
						<p>
							<b> Forgot password? </b>
						</p>
						<p>
							<span> Don't have an account? </span>  <a href="./registerUser" 
								class="pointer"> <b> Sign up here </b></a>
						</p>
					</div>
				</div>
				<div class="form-wrapper"></div>
			</div>
			<!-- END SIGN IN -->
		</div>
		<!-- END FORM SECTION -->
		<!-- CONTENT SECTION -->
		<div class="row content-row">
			<!-- SIGN IN CONTENT -->
			<div class="col align-items-center flex-col">
				<div class="text sign-in">
					<h2>Welcome User!</h2>
					<p>${message }
					</p>
				</div>
				<div class="img sign-in"></div>
			</div>
			<!-- END SIGN IN CONTENT -->
			<!-- SIGN UP CONTENT -->
			<div class="col align-items-center flex-col">
			</div>
			<!-- END SIGN UP CONTENT -->
		</div>
		<!-- END CONTENT SECTION -->
	</div>
</body>
</html>