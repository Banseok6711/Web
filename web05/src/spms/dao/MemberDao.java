package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import javax.sql.DataSource;

import spms.util.DBConnectionPool;
import spms.vo.Member;

public class MemberDao implements MemberDaoInterface{
//	Connection connection;
	Statement stmt = null;
	PreparedStatement psmt=null;
	ResultSet rs = null;
	
	DataSource ds;
	
	
	/*public void setConnection(Connection connection){
		this.connection=connection;
	}
	*/
	
//	DBConnectionPool connPool;
	/*
	public void setDbConnectionPool(DBConnectionPool connPool){
		this.connPool = connPool;
	}*/
	
	public void setDataSource(DataSource ds){
		this.ds=ds;
	}
	
	
	public List<Member> selectList() throws Exception{
		Connection connection=null;
		
		try{
			connection = ds.getConnection();
//			connection = connPool.getConnection();
			stmt=connection.createStatement();
			rs  = stmt.executeQuery(
					"SELECT MNO,MNAME,EMAIL,CRE_DATE"+
				    " FROM MEMBERS"+
							" ORDER BY MNO ASC");
			
			ArrayList<Member> members = new ArrayList<Member>();
			
			while(rs.next()){
				members.add(new Member().setNo(rs.getInt("MNO"))
						.setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL"))
						.setCreateDate(rs.getDate("CRE_DATE")));
			}
			
			return members;
			
			
		}catch(Exception e){
			throw e;
		}finally{
			try{ if(rs != null) rs.close();}catch(Exception e){}
			try{ if(stmt != null) stmt.close();}catch(Exception e){}
//			if(connection != null) connPool.returnConnection(connection);
			try{ if(connection != null) connection.close();}catch(Exception e){}
		}
	}

	@Override
	public int insert(Member member) throws Exception{
		Connection connection=null;
		int result = 0;
		
			String sql="INSERT INTO members(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE) "+
				"VALUES(?,?,?,now(),now())";
//			connection=connPool.getConnection();
			connection = ds.getConnection();
			psmt=connection.prepareStatement(sql);
			psmt.setString(1, member.getEmail());
			psmt.setString(2, member.getPassword());
			psmt.setString(3, member.getName());
			
			result =psmt.executeUpdate();	
		
			try{ if(psmt != null) psmt.close();}catch(Exception e){}
			try{ if(connection != null) connection.close();}catch(Exception e){}
		return result;
	}

	@Override
	public int delete(int no) throws Exception{
		Connection connection=null;
		int result = 0;
		
		try{
			
			String sql="DELETE FROM members WHERE MNO="+no;
			
			connection=ds.getConnection();
			stmt=connection.createStatement();
			result = stmt.executeUpdate(sql);
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
		try{ if(stmt != null) stmt.close();}catch(Exception e){}
		try{ if(connection != null) connection.close();}catch(Exception e){}
		
		return result;
	
		
	}

	@Override
	public Member selectOne(int no) throws Exception{
		Connection connection=null;
		Member member=new Member();
		
		String sql="select MNO,EMAIL,MNAME,CRE_DATE from MEMBERS "+
				"where MNO="+no;
		
		connection=ds.getConnection();
		stmt =connection.createStatement();
		rs =stmt.executeQuery(sql);
		
		if(rs.next()){
			member.setNo(rs.getInt("MNO"))
			.setEmail(rs.getString("EMAIL"))
			.setName(rs.getString("MNAME"))
			.setCreateDate(rs.getDate("CRE_DATE"));			
		}
		
		try{ if(rs != null) rs.close();}catch(Exception e){}
		try{ if(stmt != null) stmt.close();}catch(Exception e){}
		try{ if(connection != null) connection.close();}catch(Exception e){}
		
		return member;
	}

	@Override
	public int update(Member member) throws Exception{
		
		Connection connection=null;
		int result = 0;
		
		String sql="UPDATE members SET EMAIL=?,MNAME=?,MOD_DATE=now() "
				+"where MNO=?";
		connection=ds.getConnection();
		psmt=connection.prepareStatement(sql);
		psmt.setString(1, member.getEmail());
		psmt.setString(2, member.getName());
		psmt.setInt(3, member.getNo());
		
		result =psmt.executeUpdate();
		
		try{ if(psmt != null) psmt.close();}catch(Exception e){}
		try{ if(connection != null) connection.close();}catch(Exception e){}
		
		return result;
	}

	@Override
	public Member exist(String email, String password) throws Exception{
		
		Connection connection=null;
		Member member=null;
		
		String sql = "SELECT MNAME,EMAIL FROM MEMBERS"
				+ " WHERE EMAIL=? AND PWD=?";
		
		connection=ds.getConnection();
		psmt=connection.prepareStatement(sql);
		psmt.setString(1,email);
		psmt.setString(2, password);		
		rs =psmt.executeQuery();
		
		if(rs.next()){
			member=new Member()
			.setName(rs.getString("MNAME"))
			.setEmail(rs.getString("EMAIL"));
					
		}		
		
		try{if(rs != null) rs.close();}catch(Exception e){}
		try{if(psmt != null) psmt.close();}catch(Exception e){}
		try{ if(connection != null) connection.close();}catch(Exception e){}
		
		return member;
	}

}
