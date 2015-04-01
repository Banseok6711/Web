package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ServletContext sc=this.getServletContext();
//		conn = (Connection)sc.getAttribute("conn");
		try {
/*
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
*/			/*DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/studydb", "study", "study");

*/
			
			
			/*conn = (Connection)sc.getAttribute("conn");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select MNO,MNAME,EMAIL,CRE_DATE from MEMBERS");
			*/
			
//			MemberDao dao = new MemberDao();
//			dao.setConnection(conn);
			MemberDao dao=(MemberDao)sc.getAttribute("memberDao");
			ArrayList<Member> members=(ArrayList<Member>)dao.selectList();
//			ArrayList<Member> members = new ArrayList<Member>();
			
//			
//			while(rs.next()){
//				members.add(new Member()
//								.setNo(rs.getInt("MNO"))
//								.setName(rs.getString("MNAME"))
//								.setEmail(rs.getString("EMAIL"))
//								.setCreateDate(rs.getDate("CRE_DATE")));
//			}
//			
			req.setAttribute("members", members);
			RequestDispatcher rd=req.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(req, resp);
			
			
			/*
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			
			out.println("<html><head><title>회원목록</title></head>");
			out.println("<body><h1>회원목록</h1>");
			out.println("<p><a href='add'>신규 회원</a></p>");
			while(rs.next()){
				out.println(
						rs.getInt("MNO")+","+
						"<a href='update?no="+rs.getInt("MNO")+"'>"+		
						rs.getString("MNAME")+","+"</a>"+
						rs.getString("EMAIL")+" ,"+
						rs.getDate("CRE_DATE")+" "+
						"<a href='delete?no="+rs.getInt("MNO")+"'>[삭제]</a></br>");
			}
			out.println("</body></html>");
			
*/
		} catch (Exception e) {
//			throw new ServletException(e);
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}		
		finally{
			try{if (rs != null) rs.close();} catch(Exception e){}
			try{if (stmt != null) stmt.close();} catch(Exception e){}
//			try{if (conn != null) conn.close();} catch(Exception e){}			
		}


	}

}
