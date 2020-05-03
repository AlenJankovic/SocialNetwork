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
					Lorem Ipsum �r en utfyllnadstext fr�n tryck- och f�rlagsindustrin. 
					Lorem ipsum har varit standard �nda sedan 1500-talet, n�r en ok�nd boks�ttare tog att antal
					 bokst�ver och blandade dem f�r att g�ra ett provexemplar av en bok. Lorem ipsum har inte bara 
					 �verlevt fem �rhundraden, utan �ven �verg�ngen till elektronisk typografi utan st�rre f�r�ndringar. 
					 Det blev allm�nt k�nt p� 1960-talet i samband med lanseringen av Letraset-ark med avsnitt av Lorem Ipsum, 
					 och senare med mjukvaror som Aldus PageMaker.
					 I mots�ttning till vad m�nga tror, �r inte Lorem Ipsum slumpvisa ord. Det har sina r�tter i ett stycke klassiskt 
					 litteratur p� latin fr�n 45 �r f�re �r 0, och �r allts� �ver 2000 �r gammalt. 
					 Richard McClintock, en professor i latin p� Hampden-Sydney College i Virginia, �versatte ett av de mer
					  ovanliga orden, consectetur, fr�n ett stycke Lorem Ipsum och fann dess ursprung genom att studera 
					  anv�ndningen av dessa ord i klassisk litteratur. Lorem Ipsum kommer fr�n styckena 1.10.32 och 1.10.33 av 
					  "de Finibus Bonorum et Malorum" (Ytterligheterna av ont och gott) av Cicero, skriven 45 f�re �r 0. 
					  Boken �r en avhandling i teorier om etik, och var v�ldigt popul�r under ren�sanssen. Den inledande 
					  meningen i Lorem Ipsum, "Lorem Ipsum dolor sit amet...", kommer fr�n stycke 1.10.32.

					Den ursprungliga Lorem Ipsum-texten fr�n 1500-talet �r �tergiven nedan f�r de intresserade. 
					Styckena 1.10.32 och 1.10.33 fr�n 
					"de Finibus Bonorum et Malorum" av Cicero hittar du ocks� i deras originala form,
 					�tf�ljda av de engelska �vers�ttningarna av H. Rackham fr�n 1914.
			</c:if>
			
			
			</div>
		
		</div>
		
			<div class="profile-about-edit">
				<a href="${editProfileAbout}">edit</a>
			
			</div>

	</div>

</div>

