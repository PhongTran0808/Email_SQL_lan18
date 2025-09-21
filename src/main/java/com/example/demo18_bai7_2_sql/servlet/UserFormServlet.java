package com.example.demo18_bai7_2_sql.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/userForm")
public class UserFormServlet extends HttpServlet {

    private static final String DB_URL  = System.getenv("DB_URL");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASS = System.getenv("DB_PASS");

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        try (Connection conn = getConnection()) {

            if ("insert".equals(action)) {
                String fn = request.getParameter("firstname");
                String ln = request.getParameter("lastname");
                String em = request.getParameter("email");
                PreparedStatement ps = conn.prepareStatement("INSERT INTO users (firstname, lastname, email) VALUES (?, ?, ?)");
                ps.setString(1, fn);
                ps.setString(2, ln);
                ps.setString(3, em);
                ps.executeUpdate();
                ps.close();

            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String fn = request.getParameter("firstname");
                String ln = request.getParameter("lastname");
                String em = request.getParameter("email");
                PreparedStatement ps = conn.prepareStatement("UPDATE users SET firstname=?, lastname=?, email=? WHERE id=?");
                ps.setString(1, fn);
                ps.setString(2, ln);
                ps.setString(3, em);
                ps.setInt(4, id);
                ps.executeUpdate();
                ps.close();

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id=?");
                ps.setInt(1, id);
                ps.executeUpdate();
                ps.close();
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }

        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try (Connection conn = getConnection()) {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id=?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    request.setAttribute("id", rs.getInt("id"));
                    request.setAttribute("firstname", rs.getString("firstname"));
                    request.setAttribute("lastname", rs.getString("lastname"));
                    request.setAttribute("email", rs.getString("email"));
                }

                rs.close();
                ps.close();
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        // forward sang form JSP
        request.getRequestDispatcher("user-form.jsp").forward(request, response);
    }
}
