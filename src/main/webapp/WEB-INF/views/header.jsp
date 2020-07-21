<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="/findHotel">Travel Agency</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/findHotel">Home<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button"
                       aria-haspopup="true" aria-expanded="false">Management</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/addHotel">Add hotel</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/addRoom">Add room to hotel</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/users">View user orders</a>
                    </div>
                </li>
            </ul>

            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                <p3 style="color:white">Welcome ${pageContext.request.userPrincipal.name}&nbsp&nbsp|&nbsp&nbsp</p3>
                <a style="color:white" href="#" onclick="document.forms['logoutForm'].submit()">Logout</a>

            </c:if>

        </div>
    </nav>
</header>
