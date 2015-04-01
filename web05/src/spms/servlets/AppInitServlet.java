package spms.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AppInitServlet extends HttpServlet {

	@Override
	public void destroy() {
		System.out.println("AppInitServlet 마무리...");		
		super.destroy();
		Connection conn =(Connection)this.getServletContext().getAttribute("conn");
		
		try{
			if(conn != null && conn.isClosed() == false){
				conn.close();
			}
		}catch(Exception e){}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("AppInitServlet 준비...");
		super.init(config);

		ServletContext sc = this.getServletContext();
		try {
			Class.forName(sc.getInitParameter("driver"));
			Connection conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			sc.setAttribute("conn", conn);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
