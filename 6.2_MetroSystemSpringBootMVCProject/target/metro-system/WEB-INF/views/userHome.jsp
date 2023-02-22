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
			<div class="col align-items-center flex-col sign-up"></div>
			<!-- END SIGN UP -->
			<!-- SIGN IN -->
			<div class="col align-items-center flex-col sign-in">
				<div class="form-wrapper align-items-center">
					<div class="form sign-in">
						<a class="input-group" href="./showBalance"> <input type="button"
							value="Show Balance" />
						</a> <a class="input-group" href="./inputBalance"> <input type="button"
							value="Add Balance" />
							<c:if test="${not isJourneyOn }">
						</a> <a class="input-group" href="./Onboarding"> <input type="button"
							value="Swipe in" />
							</c:if>
							<c:if test="${isJourneyOn }">
						</a> <a class="input-group" href="./Onboarding"> <input type="button"
							value="Swipe out" /></c:if>
						</a> <a class="input-group" href="./logout">
							<button>Logout</button>
						</a>

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
					<h2>Welcome ${user.userName }</h2>
					<p>${message }
					</p>

				</div>
				<div class="img sign-in"></div>
			</div>
			<!-- END SIGN IN CONTENT -->
			<!-- SIGN UP CONTENT -->
			<div class="col align-items-center flex-col">
				<div class="img sign-up"></div>
				<div class="text sign-up">
					<h2>Join with us</h2>

				</div>
			</div>
			<!-- END SIGN UP CONTENT -->
		</div>
		<!-- END CONTENT SECTION -->
	</div>
</body>
<script src="<c:url value="./js/login.js" />"></script>
</html>