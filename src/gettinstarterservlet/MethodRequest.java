package gettinstarterservlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MethodRequest")
public class MethodRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MethodRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/** obt�m os nomes dos cabe�alhos **/
		Enumeration<String> headersNames = request.getHeaderNames();

		while (headersNames.hasMoreElements()) {

			String headerName = headersNames.nextElement();

			/** Obtem o valor do cabe�alho partindo do nome **/
			String headerValue = request.getHeader(headerName);

			this.log("Header: " + headerValue);
		}

		/** Obtem valor do parametro de requisi��o HTTP **/
		String user = request.getParameter("username");
		this.log(user);

		/** Obtem parametros repetidos com valores **/
		String[] colors = request.getParameterValues("color");
		for (String color : colors) {
			this.log("Cores: " + color);
		}

		/** Obtem todos os nomes de parametros **/
		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			
			String parameterValue = request.getParameter(parameterName);
			this.log("Parametro nome: " + parameterName);
			
		}
		
		/** Obtem os cookies contidos na requisi��o **/
		Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie: cookies) {
			/** Obtem o nome do cookie **/
			String name = cookie.getName();
			this.log("Cookie: " + name);
			
			/** Obtem o valor do cookie **/
			String value = cookie.getValue();
			this.log("Cookie Valor: " + value);
		}
		
		/** Obtem a sess�o associada a requisi��o **/
		HttpSession session = request.getSession();
		
		/** Armaenzar um atributo na sess�o **/
		session.setAttribute("username", "john123");
		
		/** Obtendo o atribuido da sess�o setada **/
		String username = (String) session.getAttribute("username");
		this.log("Valor de sess�o: " + username);
		
		/** invalidar a sess�o **/
		session.invalidate();
		
		/** Defini um atributo no escopo da requisi��o **/
		String attributeName = "message";
		String attributeValue = "Hello World";
		request.setAttribute(attributeName, attributeValue);
		
		/** Obtem o atributo setado no escopo da requisi��o **/
		String retrievedValue = (String) request.getAttribute(attributeName);
		this.log("Atributo de requisi��o: " + retrievedValue);
		
		/** Obtem o valor do metodo HTTP utilizado na requisi��o **/
		String method = request.getMethod();
		this.log("Metodo HTTP: " + method);
		
		/** Obtem a URL da requsi��o **/
		StringBuffer url = request.getRequestURL();
		this.log("URL da requisi��o: "+ url);
		
		/** Obtem a URI da requisi��o **/
		String uri = request.getRequestURI();
		this.log("URI da requisi��o: " + uri);
	}

}
