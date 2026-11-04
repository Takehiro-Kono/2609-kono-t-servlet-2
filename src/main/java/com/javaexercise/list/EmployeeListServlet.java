package com.javaexercise.list;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/employees")
public class EmployeeListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    EmployeeDAO dao = new EmployeeDAO();

	    // セッションからメッセージを取得
	    String message = (String) request.getSession().getAttribute("message");
	    String messageType = (String) request.getSession().getAttribute("messageType");

	    // セッションからメッセージを削除
	    request.getSession().removeAttribute("message");
	    request.getSession().removeAttribute("messageType");

	    List<Employee> employees;

	    try {
	        // DBから従業員情報を取得
	        employees = dao.getEmployees();

	    } catch (SQLException e) {

	        // DBに接続できない場合
	        employees = java.util.Collections.emptyList();

	        if (message == null) {
	            message = "登録が失敗しました。";
	            messageType = "error";
	        }

	        e.printStackTrace();
	    }

	    // JSPにデータとメッセージを渡す
	    request.setAttribute("employees", employees);
	    request.setAttribute("message", message);
	    request.setAttribute("messageType", messageType);

	    RequestDispatcher dispatcher =
	            request.getRequestDispatcher("/employeeList.jsp");

	    dispatcher.forward(request, response);
	}
}
