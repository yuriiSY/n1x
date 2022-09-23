<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <title>First JSP</title>
    <style>
        .center {
            border: 5px solid #FFFF00;
            text-align: center;
        }

    </style>
</head>
<body>
<div class="center">
    <b>IP: ${requestInfo.ip}</b><br>
    <b>User-Agent: ${requestInfo.userAgent}</b><br>
    <b>Date: ${requestInfo.time}</b>
</div>

<div class="center">
<c:forEach items="${requests}" var="requests">
            ${requests.ip}<br>
            ${requests.userAgent}<br>
            ${requests.time}<br>
</c:forEach>
</div>
</body>
</html>
