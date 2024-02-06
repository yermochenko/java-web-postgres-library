package by.vsu.jwpl.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class HelloWorld extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String[] colors = {"#FAA", "#AFA", "#AAF", "#FFA", "#FAF", "#AFF"};
		Random random = new Random();
		String color = colors[random.nextInt(colors.length)];
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>Test dynamic page</title>");
		pw.println("<style>");
		pw.printf("p { padding: 10px; border-radius: 10px; border: 2px solid red; background: %s; }\n", color);
		pw.println("b { color: blue; }");
		pw.println("</style>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<p>Hello, <b>World</b>, <i>from dynamic page</i>!!!</p>");
		pw.println("</body>");
		pw.println("</html>");
	}
}
