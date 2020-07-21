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
        <c:if test="${!empty hotels}">
            <h4> Hotels in ${hotels.get(0).country}</h4>
            <table class="table-bordered">
                <tr>
                    <th>Name</th>
                    <th>Check available rooms</th>
                </tr>
                <c:forEach items="${hotels}" var="hotel">
                    <tr>
                        <td>${hotel.name}</td>
                        <td>
                            <form method="POST" action="${contextPath}/rooms">
                                <div class="input-group input-daterange">
                                    <input type="text" class="form-control" name="startDate" value="${currDate}">
                                    <div class="input-group-addon">to</div>
                                    <input type="text" class="form-control" name="endDate" value="${currDate}">
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" name="hotelId" value="${hotel.id}"/>
                                <button type="submit" class="btn btn-primary">Check rooms</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <br/>
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
    $('.input-daterange input').each(function () {
        $(this).datepicker({
            format: 'dd/mm/yyyy',
            todayHighlight: true,
            autoclose: true,
            orientation: 'top',
            startDate: '${currDate}'
        });
    });
</script>

</body>
</html>