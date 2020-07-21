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

    <title>Hotel list</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex flex-column h-100">

<jsp:include page="header.jsp"/>

<main role="main" class="flex-shrink-0">
    <div class="container">
        <br/><br/><br/>
        <c:if test="${!empty rooms}">
            <h4> Rooms in ${rooms.get(0).hotel.name} hotel</h4>
            <table class="table-bordered">
                <tr>
                    <th>Room type</th>
                    <th>Book room</th>
                </tr>
                <c:forEach items="${rooms}" var="room">
                    <tr>
                        <td>${room.roomType.type}</td>
                        <td>
                            <form method="POST" action="${contextPath}/book_room">
                                <div class="input-group input-daterange" id="datepicker${room.id}">
                                    <input type="text" class="form-control" name="bookDate" value="${startDate}">
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" name="roomId" value="${room.id}"/>
                                <button type="submit" class="btn btn-primary">Book room</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty rooms}">
            <div class="alert alert-warning" role="alert">
                No rooms available for selected dates.
            </div>
        </c:if>
    </div>
</main>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

<!-- Include Date Range Picker -->
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

<script>
    <c:forEach items="${rooms}" var="room">
    $("#datepicker${room.id}").datepicker({
        format: 'dd/mm/yyyy',
        todayHighlight: true,
        autoclose: true,
        orientation: 'top',
        datesDisabled: ${room.bookedDatesJSArray},
        startDate: '${startDate}',
        endDate: '${endDate}'
    });
    </c:forEach>
</script>

</body>
</html>
