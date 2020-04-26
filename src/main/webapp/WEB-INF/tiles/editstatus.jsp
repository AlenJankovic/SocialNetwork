<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="row">

	<div class="col-md-8 col-md-offset-2">

		<div class="panel panel-default">

			<div class="panel-heading">
				<div class="panel-title">Edit Status Uppdate</div>
			</div>

			<div class="panel-body">
<%--set text property on statusUpdate object and displays form with textarea--%>
				
				<form:form modelAttribute="statusUpdate">
				
				<%--Submitting id and date --%>
				<form:input type="hidden" path="id"/>
				<form:input type="hidden" path="added"/>
				
				<div class="errors">									<%--display errors if exist on validation --%>				
				<form:errors path="text"/>
				</div>		
					<div class="form-group">
						<form:textarea path="text" name="text" rows="10" cols="50"></form:textarea>

					</div>
					<input type="submit" name="submit" value="Save" />
							
				</form:form>
			</div>
		</div>
	</div>

</div>
<%--Javascript text editor for status updates TinyMCE--%>
 <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>

    <script>
      tinymce.init({
        selector: 'textarea',
        plugins: 'link'
      });
    </script>
