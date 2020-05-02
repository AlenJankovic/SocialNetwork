<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">

	<div
		class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 text-center">
		<%--text-center placing text in center of div contener--%>
		<div class="message">
			<c:out value="${message }" />
		</div>
						
						
						<%--OUTPUTTING INFORMATION ABOUT EXCEPTION I SOURCE PAGE  --%>
	<!-- 
		Exception:  <c:out value="${exception}"/>						
		Failed Url: <c:out value="${url}"/>								<%--Outputting url there exception occurred --%>
		Exception message: <c:out value="${exception.message}"/>		<%--Outputting actual exception message --%>
		
		<c:forEach var="line" items="${exception.stackTrace}">			<%--Outputting exception stacktrace --%>
			<c:out value="${line}"/>
		</c:forEach>
	-->
	</div>


</div>


