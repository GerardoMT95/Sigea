<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,400i,600,600i,700" rel="stylesheet">
<title><spring:message code="title.generic" /></title>
<link rel="shortcut icon" href="${contextPath}/assets/images/favicon_dms.ico" type="image/x-icon">
<link rel="icon" href="${contextPath}/assets/images/favicon_dms.ico" type="image/x-icon">
<link href="${contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/assets/css/alertify.min.css" rel="stylesheet" />
<script type="text/javascript" src="${contextPath}/assets/js/alertify.min.js"></script>
<link rel="stylesheet" href="${contextPath}/assets/css/default.min.css">

<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"> -->

<link href="${contextPath}/assets/css/component.css" rel="stylesheet">
<link href="${contextPath}/assets/css/mycss.css" rel="stylesheet">
<link href="${contextPath}/assets/css/customAgendaB2B.css" rel="stylesheet">
<link href="${contextPath}/assets/css/sigea.css" rel="stylesheet">
<link href="${contextPath}/assets/css/spinner.css" rel="stylesheet">
<link href="${contextPath}/assets/css/backofficedms.css" rel="stylesheet">
<link href="${contextPath}/assets/css/horizontalstyle.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/assets/css/sigea2021.css?v=<%= (int) (Math.random() * 100) %>" rel="stylesheet">
<link href="${contextPath}/assets/css/sigea-responsive.css" rel="stylesheet">
<link href="${contextPath}/assets/css/fontawesome/all.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/assets/css/fontawesome/fontawesome.min.css" rel="stylesheet" type="text/css" />

<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"> -->

<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/normalize.min.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/fixedColumns.bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/style.css" />
<script type="text/javascript" src="${contextPath}/assets/js/sigeajs/polyfill.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/jquery.timepicker.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/dataTables.fixedColumns.min.js"></script>
<%-- <script type="text/javascript" src="${contextPath}/assets/js/jquery.mark.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/datatables.mark.min.js"></script> --%>

<script type="text/javascript" src="${contextPath}/assets/js/bootstrap.min.js"></script>

<script type="text/javascript" src="${contextPath}/assets/js/jquery.validate.min.js"></script> 
<script type="text/javascript" src="${contextPath}/assets/summernote/summernote.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/summernote-ext-maxlength.js"></script>
<script type="text/javascript" src="${contextPath}/assets/summernote/lang/summernote-it-IT.min.js"></script>


<script type="text/javascript" src="${contextPath}/assets/js/moment.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/moment-with-locales.js"></script>
<link href="${contextPath}/assets/css/timepicker.css" rel="stylesheet">
<!-- Datepicker -->
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/bootstrap-datepicker.min.css" />
<script type="text/javascript" src="${contextPath}/assets/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/bootstrap-datepicker.it.min.js"></script>


<link rel="stylesheet" href="${contextPath}/assets/css/wickedpicker.min.css">
<script type="text/javascript" src="${contextPath}/assets/js/wickedpicker.min.js"></script>
<link rel="stylesheet" href="${contextPath}/assets/css/selectize.bootstrap3.min.css">
<script type="text/javascript" src="${contextPath}/assets/js/selectize.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/Utilities.js?v=<%= (int) (Math.random() * 100) %>">"></script>
<script type="text/javascript" src="${contextPath}/assets/js/DTOserializer.js?v=<%= (int) (Math.random() * 100) %>">"></script>
<link rel="stylesheet" href="${contextPath}/assets/css/magnific-popup.css">
<script type="text/javascript" src="${contextPath}/assets/js/jquery.magnific-popup.min.js"></script>
<link rel="stylesheet" href="${contextPath}/assets/summernote/summernote.min.css">
