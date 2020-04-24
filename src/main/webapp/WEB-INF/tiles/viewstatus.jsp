<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ja"%>   <%--Custom tag --%>


<c:url var="url" value="/viewstatus"/>			<%--c:url tag is adding automat. if there is contextRoot  --%>

<div class="row">

	<div class="col-md-8 col-md-offset-2">

<%--PAGINATION --%>	
		
		 <ja:pagination url="${url}" page="${page}"/>		<%--passing attributes to custom pagination tag (ja)--%>

<%--Displaying status uppdates --%>	
<c:forEach  var="statusUpdate"  items="${page.content}">	<%--looping throw page content (status updates)--%>
	
	<div class="panel panel-default">

				<div class="panel-heading">
					<div class="panel-title">Status Update added on <fmt:formatDate pattern="E  d/MM/y  H:mm:s" value="${statusUpdate.added}"/> </div><%--displays datum in pattern--%>
				</div>
			<div class="panel-body">

				<c:out value="${statusUpdate.text}"/> <%--displays statusupdate text message --%>
			</div>
		</div>
 
</c:forEach>
</div>
</div>