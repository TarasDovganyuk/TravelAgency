<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Add hotel</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="d-flex flex-column h-100">

<jsp:include page="header.jsp"/>

<main role="main" class="flex-shrink-0">
    <div class="container">
        <br/><br/><br/>
        <h4>Add hotel</h4>
        <form method="POST" action="${contextPath}/addHotel">
            <label for="hotelName">Hotel name</label>
            <input type="text" class="form-control" id="hotelName" name="hotelName">
            <label for="hotelCountry">Country</label>
            <input type="text" class="form-control" id="hotelCountry" name="hotelCountry">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Add hotel</button>
        </form>
    </div>
</main>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>
