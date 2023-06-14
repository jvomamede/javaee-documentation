package gettinstarterservlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/parametersrequest")
public class ParameterRequest extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/** Pegando o parametro enviado na requisição POST **/
		String isbn = req.getParameter("isbn");
		
		Writer out = resp.getWriter();
		resp.setContentType("text/html");
		out.write("<h1>"+isbn+"</h1>");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/** Esté é um metodo GET e recebe os parametros em URL **/
		String isbn = req.getParameter("isbn");
		
		Writer out = resp.getWriter();
		resp.setContentType("text/html");
		out.write("<h1>"+isbn+"</h1>");
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/** Esté é o metodo DELETE e recebe os no corpo da requisição ou no path parameter - vamos pegar do path parameter **/
		String pathInfo = req.getPathInfo();
		
		if(pathInfo != null) {
			String pathParameter = pathInfo.substring(1);
			
			this.log("Path Paramter: " + pathParameter);
		}
		
	}
	
}
