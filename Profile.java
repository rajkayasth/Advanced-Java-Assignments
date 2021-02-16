package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	
	public void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
		String e=req.getParameter("email");
		System.out.println(e);
		
		String url="jdbc:mysql://localhost:3306/mca2020_b17?characterEncoding=latin1";
		String user="root";
		String pass="";
		
		Connection con;
		PrintWriter write=resp.getWriter();
		resp.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded");
			
			con=DriverManager.getConnection(url,user,pass);
			System.out.println("DataBase Connceted");
			
			PreparedStatement prep=con.prepareStatement("Select * from register where email=?");
			prep.setString(1,e);
			
			ResultSet rs=prep.executeQuery();
			
			System.out.println(rs.getFetchSize());
			
			if(rs.next()) {
				write.println(rs.getString("email")+ " "+rs.getString("name")+" "+rs.getString("age")+" "+rs.getString("city") );
			}else {
				write.println("User with Email "+e+" doesn't exists..");
			}
		}catch (Exception a) {
			// TODO: handle exception
			a.printStackTrace();
	}
	}
}
