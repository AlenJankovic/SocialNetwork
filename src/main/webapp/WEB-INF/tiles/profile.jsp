<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="profilePhoto" value="/profilephoto/${userId}"/>				<%--getting photo via RequestMapping /profilephoto --%>
<c:url var="editProfileAbout" value="/edit-profile-about" />

<c:url var="saveInterest" value="/save-interest"/>
<c:url var="deleteInterest" value="/delete-interest"/>

<div class="row">

	<div class="col-md-10 col-md-offset-1">
	
	<div id="profile-photo-status"></div>
	
	<div id="interestDiv">
		<ul id="interestList">
			<li>Add your interests(ex.film)!</li>
		</ul>
	
	</div>

		<div class="profile-about">


			<div class="profile-image">
				<div>
					<img src="${profilePhoto}" id="profilePhotoImage">
				</div>
				<div class="text-center">
					<a href="#" id="uploadLink">UploadPhoto</a>
				</div>
			</div>

			<div class="profile-text">

				<c:choose>
					<c:when test="${profile.about == null}">				
						Click 'edit' to add information about yourself
				</c:when>
					<c:otherwise>
						${profile.about}
					</c:otherwise>


				</c:choose>

			</div>

		</div>

		<div class="profile-about-edit">
			<a href="${editProfileAbout}">edit</a>
		</div>
		
		
		<c:url var="uploadPhotoLink" value="/upload-profile-photo"/>
		
		<form method="post" enctype="multipart/form-data" action="${uploadPhotoLink}" id="photoUploadForm">
				
					<input type="file" accept="image/*" name="file"/ id="photoFileInput">
					<input type="submit" value="upload"/>
				
					<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token }" />
		</form>

	</div>

</div>

<script>

function setUploadStatusText(text){								//manage showing and dissapearing of message on success									
	$("#profile-photo-status").text(text);
	
	window.setTimeout(function(){								//making text disappear after 2 sec. 
		$("#profile-photo-status").text("");
	},2000);
}

function uploadSuccess(data){
	$("#profilePhotoImage").attr("src","${profilePhoto}?time="+new Date());		//adding new date to differentiet
																				//url due to browser browser caching issues	
	$("#photoFileInput").val("");												//blank file name if upload is successed
	
	setUploadStatusText(data.message);											//show message Photo Uploaded from properties file 
}
	
function uploadPhoto(event){
	
	$.ajax({
		url: $(this).attr("action"),			<%--url to send data to --%>
		type:'POST',							<%--POST to server--%>
		data: new FormData(this),				<%--sending formData--%>
		processData:false,						
		contentType:false,
		success:uploadSuccess,					<%--invoks if upload is successfull--%>
		error: function(){						<%--if http respons with error cods--%> 
			setUploadStatusText("Server error")	<%--showing error message--%>
		}
	})
	
	 event.preventDefault();
	
}

function saveInterest(text){
	editInterests(text, "${saveInterest}")
}

function deleteInterest(text){
	editInterests(text, "${deleteInterest}")
}

function editInterests(text, actionUrl){
	alert(text + " : " + actionUrl)
}

$(document).ready(function(){
	
	$("#interestList").tagit({								<%--tagit configuration--%>
		
		afterTagRemoved: function(event, ui ){
			deleteInterest(ui.tagLabel);					
		},
		
		afterTagAdded: function(event, ui ){
			if(ui.duringInitialization != true){				<%--if tag is not added during initialization, add it--%>
				saveInterest(ui.tagLabel);
			}
			
		},
		
		caseSensitive: false,				   <%--Music and music is same--%>
		allowSpaces:true,					   <%--allowing spaces in interest name--%>
		tagLimit:10						       <%--limit of amount interest is 10--%>
	});								
	
	
	$("#uploadLink").click(function(event){
		event.preventDefault()							<%-- preventing default action of a-link--%>
		$("#photoFileInput").trigger("click");			<%-- trigged on click open file browser--%>
	});
	$("#photoFileInput").change(function(){				<%-- binding click event to form and submit--%>
		$("#photoUploadForm").submit();
	});
	$("#photoUploadForm").on("submit", uploadPhoto )	<%--handler intercept form on submit--%>
});
</script>
