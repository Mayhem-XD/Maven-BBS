package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.mindrot.jbcrypt.BCrypt;

import dao.UserDao;
import entity.User;

@WebServlet("/user/*")
public class UserController extends HttpServlet {
	
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uri = request.getRequestURI().split("/");
		String action = uri[uri.length-1];
		String uid, pwd, pwd2, uname, email, profile, addr;
		UserDao uDao = new UserDao();
		RequestDispatcher rd = null;
		request.setCharacterEncoding("utf-8");
		switch (action) {
		case "register":
			if (request.getMethod().equals("GET")) {
				rd = request.getRequestDispatcher("/WEB-INF/view/user/register.jsp");
				rd.forward(request, response);
			}
			else {
				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				pwd2 = request.getParameter("pwd2");
				uname = request.getParameter("uname");
				email = request.getParameter("email");
				addr = request.getParameter("addr");
				Part filePart = request.getPart("uploadFile");
				profile = filePart.getSubmittedFileName();
				filePart.write("D:/JavaWorkspace/bbs/src/main/webapp/upload"+profile);
				
				if (pwd.equals(pwd2)) {
					String hashPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
					User user = new User(uid, hashPwd, uname, email, profile, addr);
					uDao.register(user);
					response.sendRedirect("/WEB-INF/view/user/list.jsp");
				}
				
			}
			break;
		case "list":
			rd = request.getRequestDispatcher("/WEB-INF/view/user/list.jsp");
			rd.forward(request, response);
			break;
		case "login":
			if (request.getMethod().equals("GET")) {
				rd = request.getRequestDispatcher("/WEB-INF/view/user/login.jsp");
				rd.forward(request, response);
			}
			else {
				
			}
			break;
			
			
		}
		
	}


}
