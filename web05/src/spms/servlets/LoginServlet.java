package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("/auth/LoginForm.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	/*	Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;*/

		try {			
		
			ServletContext sc = this.getServletContext();
			
//			MemberDao dao =new MemberDao();
//			dao.setConnection((Connection)sc.getAttribute("conn"));
			MemberDao dao =(MemberDao)sc.getAttribute("memberDao");
			Member member =dao.exist(req.getParameter("email"), req.getParameter("password"));
			
			if(member ==null){
				RequestDispatcher rd = req.getRequestDispatcher("/auth/LoginFail.jsp");
				rd.forward(req, resp);
			}else{
				HttpSession session = req.getSession();				
				session.setAttribute("member", member);
				
				System.out.println("UserName:"+member.getName()); // Debuging
				
				resp.sendRedirect("../member/list");
			}
			
/*			conn = (Connection) sc.getAttribute("conn");
			String sql = "SELECT MNAME,EMAIL FROM MEMBERS"
					+ " WHERE EMAIL=? AND PWD=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("password"));
			
			rs= stmt.executeQuery();			
			if(rs.next()){
				Member member = new Member()
							.setEmail(rs.getString("EMAIL"))
							.setName(rs.getString("MNAME"));
				HttpSession session = req.getSession();
				session.setAttribute("member", member);
				
				resp.sendRedirect("../member/list");
				
			}else{
				RequestDispatcher rd = req.getRequestDispatcher("/auth/LoginFail.jsp");
				rd.forward(req, resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{if(rs != null) rs.close();}catch(Exception e){}
			try{if(stmt != null) stmt.close();}catch(Exception e){}
		}
	}
	*/
		}catch(Exception e){
			
		}
	}
}
