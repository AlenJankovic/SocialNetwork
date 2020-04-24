<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page"%> <%--passing in page object --%>
<%@ attribute name="url" required="true"%>

<c:set var="block" value="${empty param.b ? 0: param.b}"/>       	<%--if block.b is empty block.b=0 otherwise set bolock.b to that value --%>

Block :${block}
<div class="pagination">

<a href="${url}?b=${block - 1}">&lt;&lt;</a>												<%--<< block navigation in pagination &lt = less than--%>

	<c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
		<%--getTotalPages  returns the number of total pages()--%>

		<c:choose>
			<c:when test="${page.number != pageNumber}">									<%-- page.number is getting current page --%>
				<a href="${url}?p=${pageNumber}"><c:out value="${pageNumber}" /></a>		<%--make hyperlinks of page numbers --%>
			</c:when>

			<c:otherwise>
				<strong><c:out value="${pageNumber}" /></strong>		<%--making current page bold in list of pages--%>
			</c:otherwise>
		</c:choose>

		<c:if test="${pageNumber !=  page.totalPages}">					<%--don't display | after last page --%>
				|
		</c:if>

	</c:forEach>
	
<a href="?b=${block + 1}">&gt;&gt;</a>			<%-->> block navigation in pagination &gt= greater than--%>
</div>
