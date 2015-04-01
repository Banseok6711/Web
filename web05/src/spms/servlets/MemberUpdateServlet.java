package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import spms.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
/*
	Connection conn=null;
	Statement stmt=null;
	PreparedStatement psmt=null;
	ResultSet rs=null;
	
*/	ServletContext sc=null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
		
		try{
		
			sc =this.getServletContext();
			/*MemberDao dao =new MemberDao();
			dao.setConnection((Connection)sc.getAttribute("conn"));
			*/
			MemberDao dao =(MemberDao)sc.getAttribute("memberDao");
			Member member=dao.selectOne(Integer.parseInt(req.getParameter("no")));
			
			req.setAttribute("member", member);
			
		}catch(Exception e){
			
		}
		
		
		/*Class.forName(sc.getInitParameter("driver"));
		conn = DriverManager.getConnection(sc.getInitParameter("url")
				, sc.getInitParameter("username"),
				sc.getInitParameter("password"));
		stmt = conn.createStatement();
		String sql="select MNO,EMAIL,MNAME,CRE_DATE from MEMBERS "+
						"where MNO="+req.getParameter("no");
		rs = stmt.executeQuery(sql);
		
		rs.next();
		
		Member member =new Member().setNo(rs.getInt("MNO"))
				.setName(rs.getString("MNAME"))
				.setEmail(rs.getString("EMAIL"))
				.setCreateDate(rs.getDate("CRE_DATE"));
*/			
		
		
		RequestDispatcher rd =req.getRequestDispatcher("/member/MemberUpdateForm.jsp");
		rd.forward(req, resp);
				
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		req.setCharacterEncoding("utf-8");
		
//		System.out.println("req.getParameter(name"+req.getParameter("name"));
			
			Member member=new Member().setEmail(req.getParameter("email"))
					.setName(req.getParameter("name"))
					.setNo(Integer.parseInt(req.getParameter("no")));
			
			sc =this.getServletContext();
			MemberDao dao =(MemberDao)sc.getAttribute("memberDao");
//			MemberDao dao =new MemberDao();
//			dao.setConnection((Connection)sc.getAttribute("conn"));
			
			try {
				int result =dao.update(member);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			resp.sendRedirect("list");
		
	}
		
//		req.setCharacterEncoding("UTF-8");
/*		
		try {
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url")
					, sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			String sql="UPDATE members SET EMAIL=?,MNAME=?,MOD_DATE=now() "+
					"where MNO=?";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, req.getParameter("email"));
			psmt.setString(2, req.getParameter("name"));
			psmt.setInt(3, Integer.parseInt(req.getParameter("no")));
			
			psmt.executeUpdate();
			
			resp.sendRedirect("list");	
	}catch(Exception e){
		throw new ServletException();
	}finally{
		try{if(rs != null) rs.close();}catch(Exception e){}
		try{if(psmt != null) psmt.close();}catch(Exception e){}
		try{if(conn != null) conn.close();}catch(Exception e){}
	}
	
*/	
	
}
