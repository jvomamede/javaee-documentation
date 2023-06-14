package gettinstarterservlet;

import java.io.IOException;

import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet
public class FirstServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	public void init() throws ServletException {
		super.init();
		String ini = this.getInitParameter("initalized");
		
		this.log("Parametro de Inicialização: " + ini);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Writer out = response.getWriter();
		response.setContentType("text/html");
		out.write("<h1>Hello</h1>");
	}
	
}
