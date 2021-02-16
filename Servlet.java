package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
		
	public void service(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		 
		String email=req.getParameter("email");
		String name=req.getParameter("name");
		String age=req.getParameter("age");
		String city=req.getParameter("city");
		
		System.out.println(name.length());
		
		String url="jdbc:mysql://localhost:3306/mca2020_b17?characterEncoding=latin1";
		String user="root";
		String pass="";
		
		Connection con;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded");
			
			con=DriverManager.getConnection(url,user,pass);
			System.out.println("Connected Successfully");
			
			String insert="insert into register values(?,?,?,?)";
			
			PreparedStatement prep;
			int i=0;
			PrintWriter write=resp.getWriter();
			resp.setContentType("text/html");
			
			prep=con.prepareStatement(insert);
			prep.setString(1,email);
			prep.setString(2,name);
			prep.setString(3,age);
			prep.setString(4,city);
			
			if(!email.isEmpty() && !name.isEmpty() && !age.isEmpty() && !city.isEmpty()) {
				i=prep.executeUpdate();
				System.out.println("Number Of Record Inserted : "+i);
				write.println("<p>"+email+" "+name+"User Registerd Successfully..</p>");
			}else {
				resp.sendRedirect("index.html");
				write.println("User Not Registerd");}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
}





