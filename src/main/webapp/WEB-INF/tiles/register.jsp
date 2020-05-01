<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:url var="register" value="/register"/>

<div class="row">

	<div class="col-md-6 col-md-offset-3 col-sd-8 col-sd-offset-2">
		
		<div class="register-error">
			<form:errors path="user.*"/>
		</div>
		
		<div class="panel panel-default">
			
			<div class="panel-heading">
				<div class="panel-title">Create Account</div>
			</div>

			<div class="panel-body">

				<form:form method="post" modelAttribute="user" class="login-form">

					<div class="input-group">
						<form:input type="text" path="email" placeholder="Email"
							class="form-control" />
					</div>
					<div class="input-group">
						<form:input type="password" path="plainPassword" placeholder="Password"
							class="form-control" />
					</div>
					
					<div class="input-group">
						<form:input type="password" path="repeatPassword" placeholder="Repeat password"
							class="form-control" />
					</div>
					
					<div class="input-group">
						<button type="submit" class="btn-primary pull-right">Register</button>

					</div>
					
					
				</form:form>
			</div>
		</div>

	</div>

</div>

