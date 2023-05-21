<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<html>
<head>
<%@include file="./head.jsp"%>
</head>

	<script type="text/javascript"> 

		window.context = '${pageContext.request.contextPath}';

		
		var linkdms = '${linkDms}';

		

		
		
	</script>



<body class="internobg">


				<%@include file="./headernotlogged.jsp"%>



		<div class="row equal">
			<div class="container">
				<div class="col-md-12 col-sm-12">
					<section id="not-found" style="min-height: 350px;">
						<div class="row" style="padding:100px 0px 125px 0px">
								<div class="col-md-6 col-sm-6" style="height:500px; text-align:center;">
								<img src="${context}/assets/svg/403-error-image.svg"
									style="width: 100%; ">
							</div>
							<div class="col-md-6 col-sm-6" style="margin: 10% 0px;">
								<p class="fonttitolibold"
									style="colore: #09293; font-size: 24px">
									<strong>ACCESSO NEGATO</strong>
								</p>
								<div style="margin-top: 30px; margin-bottom: 45px;">
									<small
										style="font-size: 16px; color: #000000; line-height: 24px;">
										Non si dispone dei privilegi necessari per accedere alla risorsa.
									</small>
										<br /> <br /> 
										
									<small
										style="font-size: 14px; color: #000000; line-height: 24px;">Per Assistenza, chiama il numero
										800.174555 o scrivi a <a
										href="mailto:supporto.tecnico@aret.regione.puglia.it" style="color:#0075bf">supporto.tecnico@aret.regione.puglia.it</a><br />
										Siamo a tua disposizione dal lunedì al venerdì dalle 9.00 alle
										18.30.
									</small>
								</div>
								<p>
					
									<a href="${linkDms}home" class="btn btn-primary"
										style="margin-top: 15px; z-index: 999999; position: relative;">Torna alla Home</a>
								</p>
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
			






		<%@include file="./footer.jsp"%>



</body>
</html>
  