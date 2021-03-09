

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OperationWithDatabase
 */
@WebServlet("/OperationWithDatabase")
public class OperationWithDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperationWithDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		
		try {
			Connection con = ConnectDB.connect();
			
			if(request.getParameter("OperationType").equals("Delete")) {
				
				int Id = Integer.parseInt(request.getParameter("Id"));
				
				String delete_query = "delete from user where id ='"+ Id +"'";
				
				Statement st = con.createStatement();
				
				int check = st.executeUpdate(delete_query);
				
				if (check==1) {
					
					pw.print("<script>alert('Usuario Borrado...')</script>");
					RequestDispatcher rq = request.getRequestDispatcher("index.html");
					rq.include(request, response);
				}
				else {
					pw.print("<script>alert('Usuario No Borrado, Vuelva a Intentar')</script>");
				}
				
				return;
			}
			
			String fname, lname, phone, age, email, password, direccion, carrera, hobby, status;
			
			fname = request.getParameter("fname");
			lname = request.getParameter("lname");
			phone = request.getParameter("phone");
			age = request.getParameter("age");
			email = request.getParameter("mail");
			password = request.getParameter("pass");
			status = request.getParameter("status");
			direccion = request.getParameter("dic");
			carrera = request.getParameter("gen");
			hobby = request.getParameter("hob");
			
			
			if(request.getParameter("OperationType").equals("Add")) {
				
				String insert_query = "insert into user (fname, lname, phone, age, email, password, status) values ('"+ fname +"', '"+ lname +"', '"+ phone +"', '"+ age +"', '"+ email +"', '"+ password +"', '"+ status +"')";
				Statement st = con.createStatement();
				
				int check = st.executeUpdate(insert_query);
				
				if (check==1) {
					
					pw.print("<script>alert('Usuario Agregado...')</script>");
					RequestDispatcher rq = request.getRequestDispatcher("index.html");
					rq.include(request, response);
				}
				else {
					pw.print("<script>alert('Usuario No Agregado, Vuelva a Intentar')</script>");
				}
				
				return;
			}
			
			if(request.getParameter("OperationType").equals("Update")) {
				
				int Id = Integer.parseInt(request.getParameter("Id"));
				
				String update_query = "update user set fname='"+fname+"', lname='"+lname+"', phone='"+phone+"', age='"+age+"', email='"+email+"', direccion='"+direccion+"', carrera='"+carrera+"', hobby='"+hobby+"', password='"+ password +"' where id ='"+Id+"'";
				
				Statement st = con.createStatement();
				
				int check = st.executeUpdate(update_query);
				
				if (check==1) {
					
					pw.print("<script>alert('Usuario Actualizado...')</script>");
					RequestDispatcher rq = request.getRequestDispatcher("index.html");
					rq.include(request, response);
				}
				else {
					pw.print("<script>alert('Usuario No Actualizado, Vuelva a Intentar')</script>");
					RequestDispatcher rq = request.getRequestDispatcher("index.html");
					rq.include(request, response);
				}
				
				return;
				
			}

		}catch(Exception ex) {
			
			pw.print(ex);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
