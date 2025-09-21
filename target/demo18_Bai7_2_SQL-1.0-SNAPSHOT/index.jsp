<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Demo18 - SQL Gateway Update</title>
    <link rel="stylesheet" href="css/main.css" type="text/css"/>
</head>
<body>
<h1>Quản lý User</h1>
<p>Chọn chức năng:</p>
<form action="users" method="get">
    <button type="submit">Xem Thành Viên</button>
</form></br>

<form action="user-form.jsp" method="get" style="display:inline;">
    <input type="hidden" name="action" value="insert">
    <button type="submit">Add User</button>
</form>

</body>
</html>
