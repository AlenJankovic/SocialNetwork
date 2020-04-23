<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="url" value="/viewstatus"/>			<%--c:url tag is adding automat. if there is contextRoot  --%>

<div class="row">

	<div class="col-md-8 col-md-offset-2">

<%--PAGINATION --%>	
	<div class="pagination">
		<c:forEach var="pageNumber" begin="1" end="${page.totalPages}">		<%--getTotalPages  returns the number of total pages()--%>
		
		<c:choose>
		
			<c:when test="${page.number != pageNumber }">
				<a href="${url}?p=${pageNumber}"><c:out value="${pageNumber}"/></a>  <%--make hyperlinks of page numbers --%>
			</c:when>
			
			<c:otherwise>
				<strong><c:out value="${pageNumber}"/></strong>
			</c:otherwise>
		</c:choose>
		
		
			
			
			<c:if test="${pageNumber !=  page.totalPages}">                 <%--don't display | after last page --%>
				|
			</c:if>
		
		</c:forEach>
	</div>
<%--Displaying status uppdates --%>	
<c:forEach  var="statusUpdate"  items="${page.content}">	<%--looping throw page content (status updates)--%>
	
	<div class="panel panel-default">

				<div class="panel-heading">
					<div class="panel-title">Status Update added on <fmt:formatDate pattern="E  d/MM/y  H:mm:s" value="${statusUpdate.added}"/> </div><%--displays datum in pattern--%>
				</div>
			<div class="panel-body">

				<c:out value="${statusUpdate.text}"/> <%--displays latest text message --%>
			</div>
		</div>
 
</c:forEach>
</div>
</div>