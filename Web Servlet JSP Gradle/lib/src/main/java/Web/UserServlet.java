package Web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users=List.of(new User("Neptune","hello"),
				new User("Ashish","password"));
		Gson gson=new Gson();
		String json=gson.toJson(users);
		response.setContentType("application/json");
		response.getWriter().println(json);
		//response.getWriter().println("Get");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var name=request.getParameter("name");
		var password=request.getParameter("password");
		System.out.println(name);
		System.out.println(password);
		response.setContentType("text/html");
		response.getWriter().println("<HTML>");
		response.getWriter().println("<strong>"+name+": "+password+"</strong>");
		response.getWriter().println("</HTML>");
	}

}
