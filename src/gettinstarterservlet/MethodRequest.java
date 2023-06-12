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

		/** obtém os nomes dos cabeçalhos **/
		Enumeration<String> headersNames = request.getHeaderNames();

		while (headersNames.hasMoreElements()) {

			String headerName = headersNames.nextElement();

			/** Obtem o valor do cabeçalho partindo do nome **/
			String headerValue = request.getHeader(headerName);

			this.log("Header: " + headerValue);
		}

		/** Obtem valor do parametro de requisição HTTP **/
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
		
		/** Obtem os cookies contidos na requisição **/
		Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie: cookies) {
			/** Obtem o nome do cookie **/
			String name = cookie.getName();
			this.log("Cookie: " + name);
			
			/** Obtem o valor do cookie **/
			String value = cookie.getValue();
			this.log("Cookie Valor: " + value);
		}
		
		/** Obtem a sessão associada a requisição **/
		HttpSession session = request.getSession();
		
		/** Armaenzar um atributo na sessão **/
		session.setAttribute("username", "john123");
		
		/** Obtendo o atribuido da sessão setada **/
		String username = (String) session.getAttribute("username");
		this.log("Valor de sessão: " + username);
		
		/** invalidar a sessão **/
		session.invalidate();
		
		/** Defini um atributo no escopo da requisição **/
		String attributeName = "message";
		String attributeValue = "Hello World";
		request.setAttribute(attributeName, attributeValue);
		
		/** Obtem o atributo setado no escopo da requisição **/
		String retrievedValue = (String) request.getAttribute(attributeName);
		this.log("Atributo de requisição: " + retrievedValue);
		
		/** Obtem o valor do metodo HTTP utilizado na requisição **/
		String method = request.getMethod();
		this.log("Metodo HTTP: " + method);
		
		/** Obtem a URL da requsição **/
		StringBuffer url = request.getRequestURL();
		this.log("URL da requisição: "+ url);
		
		/** Obtem a URI da requisição **/
		String uri = request.getRequestURI();
		this.log("URI da requisição: " + uri);
	}

}
