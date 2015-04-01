package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ServletContext sc = this.getServletContext();

		try {
//			MemberDao dao = new MemberDao();
//			dao.setConnection((Connection) sc.getAttribute("conn"));
			MemberDao dao =(MemberDao)sc.getAttribute("memberDao");

			int result = dao.delete(Integer.parseInt(req.getParameter("no")));
			/*
			 * Class.forName(sc.getInitParameter("driver")); conn =
			 * DriverManager.getConnection(sc.getInitParameter("url"),
			 * sc.getInitParameter("username"),
			 * sc.getInitParameter("password"));
			 * 
			 * String
			 * sql="DELETE FROM MEMBERS WHERE MNO="+req.getParameter("no");
			 * stmt=conn.createStatement(); stmt.execute(sql);
			 */

			if (result == 1) {
				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.println("<html><head><title>삭제 성공</title>");
				out.println("<meta http-equiv='Refresh' content='1; url=list'></head>");
				out.println("<body>");
				out.println("<p>삭제 성공입니다.!</p>");
				out.println("</body></html>");
			} else {
				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.println("<html><head><title>삭제 실패</title>");
				out.println("<meta http-equiv='Refresh' content='1; url=list'></head>");
				out.println("<body>");
				out.println("<p>삭제 실패입니다.!</p>");
				out.println("</body></html>");
			}

		} catch (Exception e) {
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
