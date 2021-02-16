package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
       public void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
    	  
    	   String e=req.getParameter("email");
//   		String p=req.getParameter("pass");
   		System.out.println(e);
   		String url="jdbc:mysql://localhost:3306/mca2020_b17?characterEncoding=latin1";
		String user="root";
		String pass="";
   		Connection conn;
   		
   		PrintWriter write=resp.getWriter();	
   		resp.setContentType("text/html"); 
   		
   		try {
   			
   			Class.forName("com.mysql.jdbc.Driver");
   			System.out.println("Driver Loaded.");
   			conn=DriverManager.getConnection(url, user, pass);
   			System.out.println("DataBase Connected..");
   			
   			PreparedStatement prep=conn.prepareStatement("Select * from register where email=?");
   			prep.setString(1, e);
   			
   			ResultSet rs=prep.executeQuery();
   			
   			
   			System.out.println(rs.getFetchSize());
//   			write.println(rs.getString("FirstName")+ " "+rs.getString("LastName") );
   			if(rs.next()) {
   				write.println(rs.getString("email")+ " "+rs.getString("name") );
   				HttpSession session=req.getSession();
   				session.setAttribute("register", rs.getString("email"));
   			}else {
   				write.println("Email doesn't exists..");
   				req.getRequestDispatcher("index.html").include(req, resp);
//   				resp.sendRedirect("index.html");
   			}
   		
   		
   		
   	}catch (Exception a) {
   		// TODO: handle exception
   		a.printStackTrace();
   	}
   	
   	
   	
   	
   }
   }