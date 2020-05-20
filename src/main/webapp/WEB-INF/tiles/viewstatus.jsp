<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ja"%>   <%--Custom tag --%>

<%--c:url tag is adding automat. if there is contextRoot  --%>
<c:url var="url" value="/viewstatus"/>			

<div class="row">

	<div class="col-md-8 col-md-offset-2">

<%--PAGINATION --%>	
		<%--passing attributes to custom pagination tag (ja)--%>
		 <ja:pagination url="${url}" page="${page}" size="3"/>	
		 	
<%--Displaying status uppdates --%>	
<c:forEach  var="statusUpdate"  items="${page.content}">	<%--looping throw page content (status updates)--%>

			<c:url var="editLink" value="/editstatus?id=${statusUpdate.id}"/>
			<c:url var="deleteLink" value="/deletestatus?id=${statusUpdate.id}"/>

			<div class="panel panel-default">

				<div class="panel-heading">
					<%--displays datum in pattern--%>
					<div class="panel-title">
						Status Update added on
						<fmt:formatDate pattern="E  d/MM/y  H:mm:ss"
							value="${statusUpdate.added}" />
					</div>
				</div>
				<div class="panel-body">
					<%--displays statusupdate text message --%>
					<div>${statusUpdate.text}</div>

					<div class="edit-links pull-right">
						<a href="${editLink}">edit</a> | <a onclick="return confirm('Do you want delete status update?');"href="${deleteLink}">delete</a>
					</div>
				</div>
			</div>

		</c:forEach>
</div>
</div>