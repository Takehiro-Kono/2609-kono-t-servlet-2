package com.javaexercise.list;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	private static final String URL = "jdbc:mysql://localhost:13306/company_hr";
	private static final String USER = "root";
	private static final String PASSWORD = "test_root_password";

	private Connection getConnection() throws SQLException {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        throw new SQLException("MySQL JDBCドライバーが見つかりません。", e);
	    }

	    return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public List<Employee> getEmployees() throws SQLException {
		List<Employee> employees = new ArrayList<>();
		String sql = """
				SELECT
				e.mail,
				e.name,
				d.age,
				d.profile,
				d.birthplace,
				d.height,
				d.weight
			FROM employees e
			INNER JOIN employee_ditails d
			    ON e.uid = d.user_id
				""";
		
	
	try(
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
	){
		while (resultSet.next()) {
			Employee employee = new Employee(
				resultSet.getString("mail"),
				resultSet.getString("name"),
				resultSet.getInt("age"),
				resultSet.getString("profile"),
				resultSet.getString("birthplace"),
				resultSet.getDouble("height"),
				resultSet.getDouble("weight")
			);
			employees.add(employee);
		}
	}
	
	return employees;
	
	}
	public void registerEmployee(
	        String uid,
	        String mail,
	        String name,
	        String hashedPassword,
	        int age,
	        String profile,
	        String birthplace,
	        double height,
	        double weight
	) throws SQLException {
		 String sqlEmployees = """
			        INSERT INTO employees (
			            uid,
			            mail,
			            name,
			            password,
			            created_by,
			            created_at
			        ) VALUES (?, ?, ?, ?, ?, NOW())
			        """;
		 String sqlDetails = """
				    INSERT INTO employee_ditails (
				        user_id,
				        age,
				        profile,
				        birthplace,
				        height,
				        weight,
				        created_by,
				        created_at
				    ) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())
				    """;
		 try (
				    Connection connection = getConnection();
				    PreparedStatement statement = connection.prepareStatement(sqlEmployees);
				 PreparedStatement detailStatement = connection.prepareStatement(sqlDetails);
				
				) {
			 connection.setAutoCommit(false);
			 try {
			 statement.setString(1, uid);
			 statement.setString(2, mail);
			 statement.setString(3, name);
			 statement.setString(4, hashedPassword);
			 statement.setString(5, "system");
			 detailStatement.setString(1, uid);
			 detailStatement.setInt(2, age);
			 detailStatement.setString(3, profile);
			 detailStatement.setString(4, birthplace);
			 detailStatement.setDouble(5, height);
			 detailStatement.setDouble(6, weight);
			 detailStatement.setString(7, "system");
			 statement.executeUpdate();
			 detailStatement.executeUpdate();
			 connection.commit();
			 }
			 catch (SQLException e) {
				    connection.rollback();
				    throw e;
				}

				}
	}
	
}


