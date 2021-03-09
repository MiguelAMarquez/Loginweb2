

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

/**
 * Servlet implementation class OperationForm
 */
@WebServlet("/OperationForm")
public class OperationForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperationForm() {
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
		
		
		Connection con = ConnectDB.connect();
		
		try {
			
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
					+ "<head><title> Operation Form </title>"
					+"<style>"
						+"td,th{padding:10px 10px}"
						+"body {font-family:arial;}"
						+"table{border:1px solid black; padding: 20px"
						+"a{text-decoration:none;border:1px solid black; padding: 10px 10px;}"
						+"a:hover{color:red;}"
						+".btn{padding: 10px 20px;}"
					+"</style>"
					+"<meta charset=\"ISO-8859-1\">\r\n"
					+ "\r\n"
					+ "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl\" crossorigin=\"anonymous\">\r\n"
					+ "<!-- Latest compiled and minified CSS -->\r\n"
					+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n"
					+ "\r\n"
					+ "<!-- jQuery library -->\r\n"
					+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n"
					+ "\r\n"
					+ "<!-- Latest compiled JavaScript -->\r\n"
					+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\r\n"
					+ ""	
					+"</head>"
					+"<body>");
			pw.print("<center>"
					+"<h2>Bienvenido</h2>"
					+"<br>"
					//Total de suscriptores
					+"<div style='float:left;color:orange;border:1px solid black; padding:5px 5px;'>Total de Suscriptores <h3>"+ rs2.getInt(1) +"</h3></div>"
					//Total Activo
					+"<div style='float:left;margin-left:10px;color:blue;border:1px solid black; padding:5px 5px;'>Total Activos<h3><a href='SearchResult?Fecth=Active' style='border:none;'>"+ rs3.getInt(1) +"</a></h3></div>"
					//Añadir
					+"<div style='clear:both;'></div><div style='float:right;'><a href='OperationForm?Id=Add' style='margin-letf:10px;'>Registrar</a>"
					//Logout
					+"<a href='Logout' style='margin-left:10px;'>Salir</a></div>"
					
					+"<br><br><br>"
					
					+"<div style= 'clear:both;'><h2>Detalles del Usuario</h2>");
			String Id = request.getParameter("Id");
			
			String fname, lname, phone, age, email, password;
			
			ResultSet rs4 = null;
			
			if(Id.equals("Add")) {
				
				pw.print("<h1>Registro</h1>");
				
				pw.print("<form action='OperationWithDatabase' method='post'>\"\r\n"
						+ "				\r\n"
						+ "					<div class= \"form-row\">\r\n"
						+ "					\r\n"
						+ "					\r\n"
						+ "					</div>\r\n"
						+ "					\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>Nombre</label>\r\n"
						+ "						<input type=\"text\" class=\"form-control\" id=\"fname\" name=\"fname\" placeholder=\"Nombre\" pattern=\"[Aa-Zz]+\" required>\r\n"
						+ "						<p>El nombre debe estar compuesto de solo caracteres y no contener mas de 15</p>\r\n"
						+ "					</div>\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>Apellido</label>\r\n"
						+ "						<input type=\"text\" class=\"form-control\" id=\"lname\" name=\"lname\" placeholder=\"Apellido\" pattern=\"[Aa-Zz]+\" required>\r\n"
						+ "						<p>El apellido debe estar compuesto de solo caracteres y no contener mas de 15</p>\r\n"
						+ "					</div>\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>E-Mail</label>\r\n"
						+ "						<input type=\"text\" class=\"form-control\" id=\"mail\" name=\"mail\" placeholder=\"E-Mail\" required>\r\n"
						+ "					</div>\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>Telefono</label>\r\n"
						+ "						<input type=\"text\" class=\"form-control\" id=\"phone\" name=\"phone\" placeholder=\"Telefono\" required>\r\n"
						+ "						<p>El telefono debe estar compuesto de solo numeros y no incluir mas de 50 y minimo 7</p>\r\n"
						+ "					</div>\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>Edad (debe ser mayor de edad)</label>\r\n"
						+ "						<input type=\"number\" class=\"form-control\" id=\"age\" name=\"age\" placeholder=\"Edad\" min=\"18\" max=\"100\" required>\r\n"
						+ "					</div>\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>Password</label>\r\n"
						+ "						<input type=\"Password\" class=\"form-control\" id=\"pass\" name=\"pass\" placeholder=\"Password\" pattern=\"(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}\" required>\r\n"
						+ "						<p>Debe tener al menos un numero, una mayuscula, una minuscula y no menos de 8 caracteres</p>\r\n"
						+ "					</div>\r\n"
						+ "					<div class= \"btn btn-primary\">\r\n"
						+ "					\r\n"
						+ "						<button type=\"submit\" class=\"btn btn-primary\">Submit</button>\r\n"
						+ "						\r\n"
						+ "					</div>\r\n"
						+ "					\r\n"
						+ "					\r\n"
						+ "				\r\n"
						+ "				</form>\r\n"
						+ "				\r\n"
						+ "			\r\n"
						+ "			</div>\r\n"
						+ "		\r\n"
						+ "		\r\n"
						+ "		</div>\r\n"
						+ "	\r\n"
						+ "	\r\n"
						+ "	\r\n"
						+ "	</div>\r\n"
						+ "\r\n"
						+ "</body>\r\n"
						+ "</html>");
			}
			else {
				pw.print("<h1>Actualizar ID= "+ Id + "</h1>");
				
				String q = "select * from user where id = "+ Integer.parseInt(Id) +"";
				
				PreparedStatement pst1 = con.prepareStatement(q);
				
				rs4 = pst1.executeQuery();
				
				rs4.next();
				
				pw.print("<form action='OperationWithDatabase' method='post'>\r\n"
						+ "				\r\n"
						+ "					<div class= \"form-row\">\r\n"
						+ "					\r\n"
						+ "					</div>\r\n"
						+ "					\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>Nombre</label>\r\n"
						+ "						<input type=\"text\" class=\"form-control\" id=\"fname\" name=\"fname\" placeholder=\"Nombre\" pattern=\"[Aa-Zz]+\" required>\r\n"
						+ "						<p>El nombre debe estar compuesto de solo caracteres y no contener mas de 15</p>\r\n"
						+ "					</div>\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>Apellido</label>\r\n"
						+ "						<input type=\"text\" class=\"form-control\" id=\"lname\" name=\"lname\" placeholder=\"Apellido\" pattern=\"[Aa-Zz]+\" required>\r\n"
						+ "						<p>El apellido debe estar compuesto de solo caracteres y no contener mas de 15</p>\r\n"
						+ "					</div>\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>E-Mail</label>\r\n"
						+ "						<input type=\"text\" class=\"form-control\" id=\"mail\" name=\"mail\" placeholder=\"E-Mail\" required>\r\n"
						+ "					</div>\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>Telefono</label>\r\n"
						+ "						<input type=\"text\" class=\"form-control\" id=\"phone\" name=\"phone\" placeholder=\"Telefono\" required>\r\n"
						+ "						<p>El telefono debe estar compuesto de solo numeros y no incluir mas de 50 y minimo 7</p>\r\n"
						+ "					</div>\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>Edad (debe ser mayor de edad)</label>\r\n"
						+ "						<input type=\"number\" class=\"form-control\" id=\"age\" name=\"age\" placeholder=\"Edad\" min=\"18\" max=\"100\" required>\r\n"
						+ "					</div>\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>Direccion</label>\r\n"
						+ "						<input type=\"text\" class=\"form-control\" id=\"dic\" name=\"dic\" placeholder=\"(Opcional)\" value='"+rs4.getString(8)+"'>\r\n"
						+ "					</div>\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>A que se dedica?</label>\r\n"
						+ "						<input type=\"text\" class=\"form-control\" id=\"gen\" name=\"gen\" placeholder=\"(Opcional)\" value='"+rs4.getString(10)+"'>\r\n"
						+ "					</div>\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>Hobbys</label>\r\n"
						+ "						<input type=\"text\" class=\"form-control\" id=\"hob\" name=\"hob\" placeholder=\"(Opcional)\" value='"+rs4.getString(11)+"'>\r\n"
						+ "					</div>\r\n"
						+ "					\r\n"
						+ "					<div class= \"col-md-4 mb-3\">\r\n"
						+ "						<label>Password</label>\r\n"
						+ "						<input type=\"Password\" class=\"form-control\" id=\"pass\" name=\"pass\" placeholder=\"Password\" pattern=\"(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}\" required>\r\n"
						+ "						<p>Debe tener al menos un numero, una mayuscula, una minuscula y no menos de 8 caracteres</p>\r\n"
						+ "					</div>\r\n"
						+ "					\r\n"
						+ "						\r\n"
						+ "					</div>\r\n"
						+ "					\r\n"
						+ "					\r\n"
						+ "				\r\n"
						+ "				</form>\r\n"
						+ "				\r\n"
						+ "			\r\n"
						+ "			</div>\r\n"
						+ "		\r\n"
						+ "		\r\n"
						+ "		</div>\r\n"
						+ "	\r\n"
						+ "	\r\n"
						+ "	\r\n"
						+ "	</div>\r\n"
						+ "\r\n"
						+ "</body>\r\n"
						+ "</html>"
						+"		<input type='hidden' value='Update' name='OperationType'>"
						+"		<input type='hidden' value='"+rs4.getInt("Id")+"' name='Id'>"
						+"		<td><input type='submit' value='Update' class='btn'></td>"
						+"		</tr>"
						+"		<tr></table></form>");
				
			}
			pw.print("</table></div></body></html>");
			
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
