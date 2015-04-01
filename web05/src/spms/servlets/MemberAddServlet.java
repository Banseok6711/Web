package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

import com.mysql.jdbc.PreparedStatement;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
			

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
				RequestDispatcher rd =request.getRequestDispatcher("/member/MemberForm.jsp");
			rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletContext sc=this.getServletContext();
		// TODO Auto-generated method stub
		
//		req.setCharacterEncoding("UTF-8");
		
		
		/*Connection conn =null;
		java.sql.PreparedStatement psmt=null;
		
		ServletContext sc=this.getServletContext();
		*/
		try {
			
/*			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
*/						
			/*DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			String url="jdbc:mysql://localhost/studydb";
			String user="study";
			String password="study";
			conn = DriverManager.getConnection(url, user, password);*/
			
			/*String sql="INSERT INTO members(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE) "+
						"VALUES(?,?,?,now(),now())";
			
			psmt =conn.prepareStatement(sql);
			psmt.setString(1, req.getParameter("email"));
			psmt.setString(2, req.getParameter("password"));
			psmt.setString(3, req.getParameter("name"));
			
			psmt.executeUpdate();		*/
			
			MemberDao dao=(MemberDao)sc.getAttribute("memberDao");
			
			Member member=new Member().setName(req.getParameter("name"))
					.setEmail(req.getParameter("email"))
					.setPassword(req.getParameter("password"));
			
//			MemberDao dao =new MemberDao();
//			dao.setConnection((Connection)sc.getAttribute("conn"));
			int result =dao.insert(member);
			if(result ==1){
				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out =resp.getWriter();
				out.println("<html><head><title>회원등록 결과</title>");
				out.println("<meta http-equiv='Refresh' content='1; url=list'></head>");
				out.println("<body>");
				out.println("<p>등록 성공입니다.!</p>");
				out.println("</body></html>");
				
//				resp.sendRedirect("list");				
			}else{
				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out =resp.getWriter();
				out.println("<html><head><title>회원등록 결과</title>");
				out.println("<meta http-equiv='Refresh' content='1; url=list'></head>");
				out.println("<body>");
				out.println("<p>Fial 입니다.!</p>");
				out.println("</body></html>");
			}
			
			
			
//			resp.addHeader("Refresh", "1;url=list");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
//			throw new ServletException(e);
		}	
		
	}
	
	

}
