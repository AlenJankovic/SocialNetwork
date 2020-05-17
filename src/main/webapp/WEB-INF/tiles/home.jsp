<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="search" value="/search"/>

<div class="row status-update">
	<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

		<div class="homepage-status">Status Update :
			${statusUpdate.text}</div>
	</div>
</div>

<div class="row">
	<div class="col-md-8 col-md-offset-2 mr-auto ml-auto"">

		<form action="${search}"  method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token }" />
	
		
		<div class="input-group input-group-lg">
			<input type="text" class="form-control" name="s" placeHolder="Search Hobbies">
			
			<span class="input-group-btn">
				<button id="search-button" class="btn btn-primary" type="submit">
					Find People
				</button>
			</span>
		</div>

		</form>
</div>
</div>


