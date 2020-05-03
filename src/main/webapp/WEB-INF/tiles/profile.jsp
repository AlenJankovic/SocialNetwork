<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="img" value="/img"/>
<c:url var="editProfileAbout" value="/edit-profile-about"/>

<div class="row">

	<div class="col-md-10 col-md-offset-1">

		<div class="profile-about">
		
		
			<div class="profile-image">
				<img  src="${img}/avatar.png">
			</div>
		
			<div class="profile-text">
			
			<c:if test="${profile.about == null}">
					Lorem Ipsum är en utfyllnadstext från tryck- och förlagsindustrin. 
					Lorem ipsum har varit standard ända sedan 1500-talet, när en okänd boksättare tog att antal
					 bokstäver och blandade dem för att göra ett provexemplar av en bok. Lorem ipsum har inte bara 
					 överlevt fem århundraden, utan även övergången till elektronisk typografi utan större förändringar. 
					 Det blev allmänt känt på 1960-talet i samband med lanseringen av Letraset-ark med avsnitt av Lorem Ipsum, 
					 och senare med mjukvaror som Aldus PageMaker.
					 I motsättning till vad många tror, är inte Lorem Ipsum slumpvisa ord. Det har sina rötter i ett stycke klassiskt 
					 litteratur på latin från 45 år före år 0, och är alltså över 2000 år gammalt. 
					 Richard McClintock, en professor i latin på Hampden-Sydney College i Virginia, översatte ett av de mer
					  ovanliga orden, consectetur, från ett stycke Lorem Ipsum och fann dess ursprung genom att studera 
					  användningen av dessa ord i klassisk litteratur. Lorem Ipsum kommer från styckena 1.10.32 och 1.10.33 av 
					  "de Finibus Bonorum et Malorum" (Ytterligheterna av ont och gott) av Cicero, skriven 45 före år 0. 
					  Boken är en avhandling i teorier om etik, och var väldigt populär under renäsanssen. Den inledande 
					  meningen i Lorem Ipsum, "Lorem Ipsum dolor sit amet...", kommer från stycke 1.10.32.

					Den ursprungliga Lorem Ipsum-texten från 1500-talet är återgiven nedan för de intresserade. 
					Styckena 1.10.32 och 1.10.33 från 
					"de Finibus Bonorum et Malorum" av Cicero hittar du också i deras originala form,
 					åtföljda av de engelska översättningarna av H. Rackham från 1914.
			</c:if>
			
			
			</div>
		
		</div>
		
			<div class="profile-about-edit">
				<a href="${editProfileAbout}">edit</a>
			
			</div>

	</div>

</div>

