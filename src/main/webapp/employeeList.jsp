<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>社員一覧</title>
</head>
<body>
	<%
	String message = (String) request.getAttribute("message");
	String messageType = (String) request.getAttribute("messageType");

	if (message != null && !message.isBlank()) {
	    String messageColor = "success".equals(messageType) ? "#007bff" : "#dc3545";
	%>
	    <p style="color: <%= messageColor %>; font-weight: bold;">
	        <%= message %>
	    </p>
	<%
	}
	%>
	<h1>社員一覧</h1>
	<p>
	    <a href="${pageContext.request.contextPath}/employees/create">
	        新しい従業員を登録する
	    </a>
	</p>
	<table border="1"> 
		<thead>
			<tr>
				<th>メールアドレス</th>
				<th>名前</th>
				<th>年齢</th>
				<th>出身地</th>
				<th>身長（cm）</th>
				<th>体重（kg）</th>
			</tr>
		</thead>
		<tbody>
			<%
			java.util.List<com.javaexercise.list.Employee> employees =
				(java.util.List<com.javaexercise.list.Employee>) request.getAttribute("employees");
					
			if (employees == null || employees.isEmpty()) {
			
				%>
				<tr>
					<td colspan="6">従業員情報はありません</td>
				</tr>
				<%
				} else {
					for (com.javaexercise.list.Employee employee : employees) {
						
					%>
				<tr>
					<td><%= employee.getMail() %></td>
					<td><%= employee.getName() %></td>
					<td><%= employee.getAge() %></td>
					<td><%= employee.getBirthplace() %></td>
					<td><%= employee.getHeight() %> cm</td>
					<td><%= employee.getWeight() %> kg</td>
				</tr>
		<%
			}		
		}
		%>
		</tbody>
	</table>
	<p>総ユーザー数：<%= employees == null ? 0 : employees.size() %>名</p>
</body>
</html>