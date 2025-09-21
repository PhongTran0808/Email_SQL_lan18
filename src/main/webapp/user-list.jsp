<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <link rel="stylesheet" href="css/main.css" type="text/css"/>
</head>
<body>
<h2>User List</h2>

<nav>
    <form action="index.jsp" method="get" style="display:inline;">
        <button type="submit">Home</button>
    </form>
    <form action="users" method="get" style="display:inline;">
        <button type="submit">Xem Thành Viên</button>
    </form>
</nav>

${requestScope.sqlResult}
</body>
</html>
