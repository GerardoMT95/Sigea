<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col-md-1 col-sm-2 sidebar-offcanvas" style="height: 100%;">
	<ul data-spy="affix" data-offset-top="193" id="affix" class="nav nav-stacked affix-top" style="margin-top: 15px;">
		<li class="menuli" id="Back"><a href="javascript:goToDms()"><i class="fas fas fa-chevron-left sidebar"></i><span style="line-height: 40%;"><br /></span><span
				class="limenu" style="font-size: 12px !important;"><spring:message code="label.back" /></span></a></li>
	</ul>
</div>

<script>
	function goToDms() {
		window.location.replace('https://www.dms.puglia.it/');
	}
</script>