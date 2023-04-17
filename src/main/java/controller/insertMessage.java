package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessageDAO;
import dto.MessageDTO;

@WebServlet("/insertMessage")
public class insertMessage extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getRequestURI();
		if(cmd.equals("/insert.message")) {
			String writer = request.getParameter("writer");
			String message = request.getParameter("message");
			
			MessageDAO dao = MessageDAO.getInstacne();
			MessageDTO dto = new MessageDTO(0, writer,message);
			dao.insert(dto);
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
