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
					<c:if test="${not isJourneyOn }"><spring:form action="./boarding" method="post">
							<div class="input-group"><div>Select Boarding station :</div></div>

								<div class="input-group">
								<spring:select id="stationId" path="number" >
									<c:forEach items="${stations }" var="station">
										<spring:option  value="${station.number }"></spring:option>
									</c:forEach>
								</spring:select>
								</div>
							
							<button>Board</button>
						</spring:form>
						</c:if>
						<c:if test="${isJourneyOn }"><spring:form action="./offboarding" method="post">
							<div class="input-group"><div>Select Boarding station :</div></div>

								<div class="input-group">
								<spring:select id="stationId" path="number" >
									<c:forEach items="${stations }" var="station">
										<spring:option  value="${station.number }"></spring:option>
									</c:forEach>
								</spring:select>
								</div>
							
							<button>Off-Board</button>
						</spring:form>
						</c:if>
						<a class="input-group" href="./userHome">
							<button>Back</button>
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
					<p>${message }</p>

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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous"></script>
	<script src="<c:url value="./js/login.js" />"></script>

</body>
</html>