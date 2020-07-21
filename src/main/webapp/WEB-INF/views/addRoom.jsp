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

    <title>Add room to hotel</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="d-flex flex-column h-100">

<jsp:include page="header.jsp"/>

<main role="main" class="flex-shrink-0">
    <div class="container">
        <br/><br/><br/>
        <c:if test="${!empty hotels}">
            <h4>Add room to hotel</h4>
            <form method="POST" action="${contextPath}/addRoom">
                <div class="form-group">
                    <label for="selectHotel">Select hotel</label>
                    <select class="form-control" id="selectHotel" name="selectHotel">
                        <c:forEach items="${hotels}" var="hotel">
                            <option value="${hotel.id}">${hotel.name} (${hotel.country})</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="selectRoomType">Select room type</label>
                    <select class="form-control" id="selectRoomType" name="selectRoomType">
                        <c:forEach items="${roomTypes}" var="roomType">
                            <option value="${roomType.id}">${roomType.type}</option>
                        </c:forEach>
                    </select>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary">Add room</button>
            </form>
        </c:if>
        <c:if test="${empty hotels}">
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
