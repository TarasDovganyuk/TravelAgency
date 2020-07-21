<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Find hotel</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/sticky-footer-navbar/">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../resources/css/sticky-footer-navbar.css" rel="stylesheet">
</head>
<body class="d-flex flex-column h-100">

<jsp:include page="header.jsp"/>

<main role="main" class="flex-shrink-0">
    <div class="container">
        <c:if test="${!empty countries}">
            <form method="POST" action="${contextPath}/findHotel">
                <div class="form-group">
                    <label for="selectCountry">Select country</label>
                    <select class="form-control" id="selectCountry" name="selectCountry">
                        <c:forEach items="${countries}" var="country">
                            <option>${country}</option>
                        </c:forEach>
                    </select>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary">Find hotels</button>
            </form>
        </c:if>
        <c:if test="${empty countries}">
            <div class="alert alert-warning" role="alert">
                No hotels in system available.
            </div>
        </c:if>

    </div>
</main>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>