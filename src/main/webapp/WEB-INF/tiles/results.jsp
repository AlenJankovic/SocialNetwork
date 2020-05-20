<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ja"%>   <%--Custom tag --%>

<div class="row">
	<div class="col-md-12 results-nonresult">
		<c:if test="${empty page.content}">
			<strong>No results found</strong>
		</c:if>
	</div>
</div>


<c:url var="searchUrl" value="/search?s=${s}"/>	
	
	<div class="row">
		<div class="col-md-12">
	 		<ja:pagination url="${searchUrl}" page="${page}" size="5"/>	
	 	</div>
	</div>

<c:forEach var="result" items="${page.content}">

	<c:url var="profilePhoto" value="/profilephoto/${result.userId}" />
	<c:url var="profileLink" value="/profile/${result.userId}" />

	<div class="row person-row">
		<%--display row to each user --%>
		<div class="col-md-12">
			<div class="results-photo">
				<a href="${profileLink}"><img src="${profilePhoto}"
					id="profilePhotoImage"></a>
			</div>

			<div class="results-details">
				<div class="results-name">
					<a href="${profileLink}"><c:out value="${result.firstName}" />
						<c:out value="${result.lastName}" /></a>
				</div>
				<div class="results-interests">
					<c:forEach var="interests" items="${result.interests}"
						varStatus="status">
						<c:url var="interestLink" value="/search?s=${interests}" />
						<!--searching after same interests  -->
						<a href="${interestLink}"><c:out value="${interests}" /></a>

						<c:if test="${!status.last}"> | </c:if>
					</c:forEach>
				</div>
			</div>



		</div>
	</div>

</c:forEach>