<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="row">

	<div class="col-md-8 col-md-offset-2">
	
	<div class="errors">
						<form:errors path="profile.*"/>				<%--Path to errors object for data binding --%>
					</div>

		<div class="panel panel-default">

			<div class="panel-heading">
				<div class="panel-title">Edit your about text</div>
			</div>

			<div class="panel-body">
<%--set text property on statusUpdate object and displays form with textarea--%>
				
				<form:form modelAttribute="profile">
				
					<div class="form-group">
						<form:textarea path="about" name="about" rows="10" cols="50"></form:textarea>

					</div>
					<input type="submit" name="submit" value="Save" />
							
				</form:form>
			</div>
		</div>
	</div>

</div>
<%--Javascript text editor for editing profile about TinyMCE--%>
 <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>

    <script>
      tinymce.init({
        selector: 'textarea'
       });
    </script>
