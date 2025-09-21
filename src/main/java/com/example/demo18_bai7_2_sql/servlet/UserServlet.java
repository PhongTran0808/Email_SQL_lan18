package com.example.demo18_bai7_2_sql.servlet;

import com.example.demo18_bai7_2_sql.SQL.SQLUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String sqlResult = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbURL = "jdbc:mysql://localhost:3306/demo18_web?useSSL=false&serverTimezone=UTC";
            String username = "root";
            String password = "123123@";
            Connection connection = DriverManager.getConnection(dbURL, username, password);

            String sql = "SELECT * FROM users";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            StringBuilder table = new StringBuilder();
            table.append("<table>");
            table.append("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Action</th></tr>");
            while (rs.next()) {
                int id = rs.getInt("id");
                String fn = rs.getString("firstname");
                String ln = rs.getString("lastname");
                String em = rs.getString("email");
                table.append("<tr>");
                table.append("<td>").append(id).append("</td>");
                table.append("<td>").append(fn).append("</td>");
                table.append("<td>").append(ln).append("</td>");
                table.append("<td>").append(em).append("</td>");
                table.append("<td>")
                        .append("<a href='userForm?action=edit&id=").append(id).append("'>Edit</a> | ")
                        .append("<a href='userForm?action=delete&id=").append(id)
                        .append("' onclick=\"return confirm('Bạn có chắc chắn muốn xóa User này không?');\">Delete</a>")
                        .append("</td>");
                table.append("</tr>");
            }
            table.append("</table>");

            sqlResult = table.toString();

            rs.close();
            ps.close();
            connection.close();

        } catch (Exception e) {
            sqlResult = "<p>Error: " + e.getMessage() + "</p>";
        }

        request.setAttribute("sqlResult", sqlResult);
        request.getRequestDispatcher("user-list.jsp").forward(request, response);
    }
}
