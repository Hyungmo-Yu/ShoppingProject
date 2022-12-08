<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
 
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>
		<h1>Successfully Connected to the Database!</h1>
		<%
		Connection conn = null;
		String driverName = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driverName);
		String serverName = "127.0.0.1";
		String serverPort = "1521";
		String sid = "orcl";
		String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort + ":" + sid;
		String username = "c##shoppingproject";
		String password = "password";
		conn = DriverManager.getConnection(url, username, password);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from c##shoppinguser");
		while (rs.next()) 
		{
			%>
		
			User ID :
			<%=rs.getInt(1)%><br /> User Type :
			<%=rs.getString(2)%><br /> User Name :
			<%=rs.getString(3)%><br /> Password :
			<%=rs.getString(4)%><br /> Birth Date :
			<%=rs.getDate(5)%><br /> Gender :
			<%=rs.getString(6)%><br /> Email :
			<%=rs.getString(7)%><br /> Contact :
			<%=rs.getString(8)%><br /> Address :
			<%=rs.getString(9)%>
		
			<%
		}
		rs.close();
		%>
	</body>
</html>