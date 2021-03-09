

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
		
		HttpSession session = request.getSession(false);
		
		if(session== null) {
			
			response.sendRedirect("Login");
		}
		
		try {
			
			Connection con = ConnectDB.connect();
			
			String fetch_user = "select * from user";
			String count_user = "select count(*) from user";
			String active_user = "select count(*) from user where status = 'Active'";
			
			PreparedStatement pst = con.prepareStatement(fetch_user);
			ResultSet rs = pst.executeQuery();
			
			PreparedStatement pst2 = con.prepareStatement(count_user);
			ResultSet rs2 = pst2.executeQuery(count_user);
			rs2.next();
			
			PreparedStatement pst3 = con.prepareStatement(active_user);
			ResultSet rs3 = pst3.executeQuery(active_user);
			rs3.next();
						
			
			pw.print("<html>"
					+ "<head><title> Profile </title>"
					+"<style>"
					
							+"td,th{padding:14px 30px}"
							+"body {font-family:arial;}"
							+"table{border:1px solid black; padding: 20px"
							+"a{text-decoration:none;border:1px solid black; padding: 10px 10px;}"
							+"a:hover{color:red;}"
					+"</style>"
					+"</head>"
					+"<body>"
					+"<center>"
					+"<h2>Bienvenido</h2>"
					+"<br>"
					//Total de suscriptores
					+"<div style='float:left;color:orange;border:1px solid black; padding:5px 5px;'>Total de Suscriptores <h3>"+ rs2.getInt(1) +"</h3></div>"
					//Total Activo
					+"<div style='float:left;margin-left:10px;color:blue;border:1px solid black; padding:5px 5px;'>Total Activos<h3><a href='SearchResult?Fecth=Active' style='border:none;'>"+ rs3.getInt(1) +"</a></h3></div>"
					//Logout
					+"<div style='float:right;'><a href='Logout' style='margin-left:10px;'>Salir</a></div>"
					
					+"<div style= 'clear:both;'><h2>Detalles del Usuario</h2>");
			pw.print("<table margin-top:-80px;><tr><th>Id</th>"
					+"<th>Email</th>"
					+"<th>Nombre</th>"
					+"<th>Apellido</th>"
					+"<th>Telefono</th>"
					+"<th>Edad</th>"
					+"<th>Direccion</th>"
					+"<th>Genero</th>"
					+"<th>Hobby</th></tr>");
			while(rs.next()) {
				
				

				
				pw.print("<tr><td>"+ rs.getInt(3) + "</td>"
							+"<td>"+ rs.getString(1) + "</tdr>"
							+"<td>"+ rs.getString(4) + "</tdr>"
							+"<td>"+ rs.getString(5) + "</tdr>"
							+"<td>"+ rs.getString(6) + "</tdr>"
							+"<td>"+ rs.getString(7) + "</tdr>"
							+"<td>"+ rs.getString(8) + "</tdr>"
							+"<td>"+ rs.getString(10) + "</tdr>"
							+"<td>"+ rs.getString(11) + "</tdr>"
							
							+"</tr><br><br>");			
			}
			
			try {
				int NuId = (int)request.getAttribute("Nu");
				
				
				pw.print(//Actualizar
				"<div style='clear:both;'></div><div style='float:right;'><a href='OperationForm?Id="+ NuId + "'>Actualizar Datos</a>"
				//Borrar
				+"<a href='OperationWithDatabase?OperationType=Delete&Id="+ NuId + "' style='margin-left:10px;'>Borrar Usuario</a></div>"
				+"<br><br><br>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pw.print("</table></div></body></html>");
			con.close();
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
