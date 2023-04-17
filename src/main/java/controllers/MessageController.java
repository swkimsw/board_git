package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.message")
public class MessageController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html; charset=utf8;");

		String cmd = request.getRequestURI();
		try {
			if(cmd.equals("/insert.message")) {
				MessageDAO dao = MessageDAO.getInstance();
				String writer = request.getParameter("writer");
				String message = request.getParameter("message");
				MessageDTO dto = new MessageDTO(0, writer, message);
				int result = dao.insert(dto);
				response.sendRedirect("index.jsp");
			}
			else if(cmd.equals("/select.message")) {
				MessageDAO dao = MessageDAO.getInstance();
				List<MessageDTO> result = dao.selectAll();
				request.setAttribute("list", result);
				request.getRequestDispatcher("list.jsp").forward(request, response);
			}
			else if(cmd.equals("/update.message")) {
				MessageDAO dao = MessageDAO.getInstance();
				int id = Integer.parseInt(request.getParameter("id"));
				String writer = request.getParameter("writer");
				String message = request.getParameter("message");
				MessageDTO dto = new MessageDTO(id,writer,message);
				int result = dao.update(dto);
				response.sendRedirect("/select.message");
			}
			else if(cmd.equals("/delete.message")) {
				MessageDAO dao = MessageDAO.getInstance();
				int id = Integer.parseInt(request.getParameter("id"));
				MessageDTO dto = new MessageDTO(id,null,null);
				int result = dao.delete(dto);
				response.sendRedirect("/select.message");
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
