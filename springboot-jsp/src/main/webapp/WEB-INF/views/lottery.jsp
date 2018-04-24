<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <title>抽奖</title>
    <link rel="stylesheet" type="text/css" href="/style.css">
</head>
<br>
本轮准备名单：<br>
<c:forEach items="userList" var="user">
    ${user.name}
</c:forEach>

</body>
</html>