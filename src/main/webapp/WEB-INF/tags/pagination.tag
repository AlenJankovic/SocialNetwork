<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page"%> <%--passing in page object --%>
<%@ attribute name="url" required="true"%>

<c:set var="paramListSeparator" value="${fn:contains(url, '?') ? '&': '?'}"/>	<%-- if base url have ? change to & otherwise ? --%>

<%@ attribute name="size" required="false"%>		<%--Number of page numbers to display at once --%>

<c:set var="block" value="${empty param.b ? 0: param.b}"/>       	<%--if block.b is empty block.b=0 otherwise set bolock.b to that value --%>
<c:set var="size" value="${empty size ? 0: size}"/>  				<%--if size is not set, set to 0 otherwise set to size --%>

<%--Block :${block} --%>
<%-- Size :${size}--%>

<%--start page in block --%>
<c:set var="startPage" value="${block * size + 1 }" /> 
<%--ending page in block --%>
<c:set var="endPage" value="${(block + 1) * size }" />

<c:set var="endPage" value="${endPage > page.totalPages ? page.totalPages : endPage }" />	<%--getTotalPages  returns the number of total pages()--%>

<%--StartPage :${startPage}--%>
<%--EndPage :${endPage}--%>

<c:if test="${page.totalPages != 1 }">    <%--if amount of pages is not equal to 1 go to pagination otherwise not--%>
	<div class="pagination">
		
		<%-- displaying navigation << only if block is not 0 --%>
		<c:if test="${block != 0 }">
				<%--<< block navigation in pagination &lt = less than--%>
				<a href="${url}${paramListSeparator}b=${block - 1}&p=${(block-1)*size +1}">&lt;&lt;</a>
			
		</c:if>
	
		<c:forEach var="pageNumber" begin="${startPage}" end="${endPage}">
			
			<c:choose>
				<c:when test="${page.number != pageNumber -1}">      	 <%--????? --%>						<%-- page.number is getting current page --%>
					<a href="${url}${paramListSeparator}p=${pageNumber}&b=${block}"><c:out value="${pageNumber}" /></a>    <%--make hyperlinks of page numbers --%>
				</c:when>
	
				<c:otherwise>
					<%--making current page bold in list of pages--%>
					<strong><c:out value="${pageNumber}" /></strong>
					
				</c:otherwise>
			</c:choose>
			<%--not displaying | after last page --%>
			<c:if test="${pageNumber !=  endPage}">
				|
			</c:if>
	
		</c:forEach>
				<%-->> block navigation in pagination &gt= greater than--%>
			
			<c:if test="${endPage != page.totalPages}">		<%-- displaying navigation >> only if endPage is not equal totalPages --%>
				<a href="${url}${paramListSeparator}b=${block + 1}&p=${(block+1)*size+1}">&gt;&gt;</a> <%--navigate through blocks and marking först page in block of pages --%>
			</c:if>
	</div>
</c:if>