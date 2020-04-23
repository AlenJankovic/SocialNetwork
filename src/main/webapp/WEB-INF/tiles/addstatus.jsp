<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="row">

	<div class="col-md-8 col-md-offset-2">

		<div class="panel panel-default">

			<div class="panel-heading">
				<div class="panel-title">Add a Status Uppdate</div>
			</div>

			<div class="panel-body">
<%--set text property on statusUpdate object and displays form with textarea--%>
				<form:form modelAttribute="statusUpdate">
				
				<div class="errors">									<%--display errors if exist on validation --%>				
				<form:errors path="text"/>			
				</div>		
					<div class="form-group">
						<form:textarea path="text" name="text" rows="10" cols="50"></form:textarea>

					</div>
					<input type="submit" name="submit" value="Add status" />
							
				</form:form>
			</div>
		</div>
<%--display latest text and date --%>		
		<div class="panel panel-default">

			<div class="panel-heading">
				<div class="panel-title">Status Update added on <fmt:formatDate pattern="E  d/MM/y  H:mm:s" value="${latestStatusUpdate.added}"/> </div><%--displays datum in pattern--%>
			</div>
			<div class="panel-body">

				<c:out value="${latestStatusUpdate.text}"/> <%--displays latest text message --%>
				
			</div>
		</div>
		

	</div>

</div>

