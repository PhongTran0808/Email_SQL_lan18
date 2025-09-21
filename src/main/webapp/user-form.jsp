<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
    <link rel="stylesheet" href="css/main.css" type="text/css"/>
</head>
<body>
<h2>User Form</h2>

<nav>
    <form action="index.jsp" method="get" style="display:inline;">
        <button type="submit">Home</button>
    </form>
    <form action="users" method="get" style="display:inline;">
        <button type="submit">Xem Thành Viên</button>
    </form>
</nav>

<form action="userForm" method="post">
    <!-- ẩn action để phân biệt insert/update -->
    <input type="hidden" name="action" value="${param.action != null ? param.action : 'insert'}">
    <input type="hidden" name="id" value="${id != null ? id : ''}">

    <label>First Name:</label>
    <input type="text" name="firstname" value="${firstname != null ? firstname : ''}" required><br><br>

    <label>Last Name:</label>
    <input type="text" name="lastname" value="${lastname != null ? lastname : ''}" required><br><br>

    <label>Email:</label>
    <input type="email" name="email" value="${email != null ? email : ''}" required><br><br>

    <button type="submit">Save</button>
</form>
</body>
</html>
