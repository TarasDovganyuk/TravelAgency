<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${user.username} orders</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex flex-column h-100">

<jsp:include page="header.jsp"/>

<main role="main" class="flex-shrink-0">
    <div class="container">
        <br/><br/><br/>
        <c:if test="${!empty orders}">
            <h4>Order list for ${user.username}</h4>
            <table class="table-bordered">
                <tr>
                    <th>Hotel</th>
                    <th>Room type</th>
                    <th>Date</th>
                </tr>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.room.hotel.name}</td>
                        <td>${order.room.roomType.type}</td>
                        <td>${order.bookDate}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty orders}">
            <div class="alert alert-warning" role="alert">
                No orders for user ${user.username}
            </div>
        </c:if>
    </div>
</main>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
