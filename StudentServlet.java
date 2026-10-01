package com.student.controller;

import com.student.dao.StudentDAO;
import com.student.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Student> students = dao.getAllStudents();
            request.setAttribute("students", students);
            request.getRequestDispatcher("/students.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Unable to load students", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                Student student = new Student(
                    0,
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("course")
                );
                dao.addStudent(student);
            } else if ("update".equals(action)) {
                Student student = new Student(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("course")
                );
                dao.updateStudent(student);
            } else if ("delete".equals(action)) {
                dao.deleteStudent(Integer.parseInt(request.getParameter("id")));
            }

            response.sendRedirect(request.getContextPath() + "/students");
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Student operation failed", e);
        }
    }
}
