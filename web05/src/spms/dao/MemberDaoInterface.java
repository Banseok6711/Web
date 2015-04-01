package spms.dao;

import spms.vo.Member;

public interface MemberDaoInterface {
	
	public int insert(Member member) throws Exception;
	public int delete(int no) throws Exception;
	public Member selectOne(int no) throws Exception;
	public int update(Member member) throws Exception;
	public Member exist(String email,String password) throws Exception;

}
