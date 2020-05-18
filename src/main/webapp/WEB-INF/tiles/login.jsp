<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="loginUrl" value="/login"/>
<c:url var="registerUrl" value="/register"/>

<div class="row">
	<div class="col-md-6 col-md-offset-3 col-sd-8 col-sd-offset-2 register-prompt">
		Please log in or <a href="${registerUrl}">click here to create account.</a>
	</div>
</div>

<div class="row">

	<div class="col-md-6 col-md-offset-3 col-sd-8 col-sd-offset-2">

		<c:if test="${param.error != null }">			<%--Error message login --%>
			<div class="login-error">Wrong username or password!</div>
		</c:if>
		
		<div class="panel panel-default">
			
			<div class="panel-heading">
				<div class="panel-title">User Log in</div>
			</div>

			<div class="panel-body">

				<form method="post" action="${loginUrl}" class="login-form">

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token }" />

					<div class="input-group">
						<input type="text" name="username" placeholder="Username"
							class="form-control" />
					</div>
					<div class="input-group">
						<input type="password" name="password" placeholder="Password"
							class="form-control" />
					</div>
					<div class="input-group">
						<button type="submit" class="btn-primary pull-right">Sign
							in</button>

					</div>
				</form>
			</div>
		</div>

	</div>

</div>

