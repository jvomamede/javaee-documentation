package servlets;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private UserDAO userDAO;
	
    public LoginServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
    	super.init();
    	this.userDAO = new UserDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		this.userDAO.salvar(user);
		
		this.log(username + " " + password);
		
		response.setContentType("text/html");
		User userReturn = this.userDAO.findByUsername(user.getUsername());
		
		Writer out = response.getWriter();
		
		out.write("<h1>" + userReturn.getId()+"</h1>");
	}

}
