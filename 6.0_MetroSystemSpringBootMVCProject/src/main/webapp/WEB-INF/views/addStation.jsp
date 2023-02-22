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
<title>Registered</title>
</head>
<body>
	<%@ include file="./common/header.jspf"%>
	<div id="container" class="container sign-up">
		<!-- FORM SECTION -->
		<div class="row">
			<!-- SIGN UP -->
			<div class="col align-items-center flex-col sign-up">
				<div class="form-wrapper align-items-center">
					<div class="form sign-up">
						<spring:form action="./addStation" method="post">
							<div class="input-group">
								<input name="name" type="text" placeholder="Station name" />
							</div>
							<button>Add</button>
						</spring:form>
						<a class="input-group" href="./">
							<button>Home</button>
						</a>
					</div>
				</div>

			</div>
			<!-- END SIGN UP -->
			<!-- SIGN IN -->
			<div class="col align-items-center flex-col sign-in">
			</div>
			<!-- END SIGN IN -->
		</div>
		<!-- END FORM SECTION -->
		<!-- CONTENT SECTION -->
		<div class="row content-row">
			<!-- SIGN IN CONTENT -->
			<div class="col align-items-center flex-col">
			</div>
			<!-- END SIGN IN CONTENT -->
			<!-- SIGN UP CONTENT -->
			<div class="col align-items-center flex-col">
				<div class="img sign-up"></div>
				<div class="text sign-up">
				<c:if test="${not empty station }">
					<h2> Station Number: ${station.number} </h2>
					<p>${message }
					</p>
					</c:if>
				<c:if test="${empty station }">
					<h2> Welcome </h2>
					</c:if>
				</div>
			</div>
			<!-- END SIGN UP CONTENT -->
		</div>
		<!-- END CONTENT SECTION -->
	</div>
</body>
</html>