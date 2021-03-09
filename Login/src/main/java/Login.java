

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		PrintWriter pv = response.getWriter();
		
		request.getRequestDispatcher("Logout").include(request, response);
		
		pv.print( "<html>"
				+"<style>"
					+"body{font-family:arial;}"
				+"</style>"
				+"</head>"
				+"<body>"
				+"<center>"
				+"<h1>Login</h1>"
				+" <form action= 'Check_User' method='post'>"
				+"	<input type='text' name='mail' placeholder='E-Mail' required>"
				+"	<input type='password' name='pass' placeholder='Password' pattern='(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}' required>"
				+"  <input type= 'submit' value= 'Go'>"
				+"<div style='clear:both;'></div><div style='float:right;'><a href='OperationForm?Id=Add'>Registrarse</a></div>"
				+"</form>"
				+"</center>"
				+"</body>"
				+"</html>");
				
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
