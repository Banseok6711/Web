<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 
<jsp:useBean id="member"
			scope="request"
			class="spms.vo.Member"
 />
		 --%>
		 
		 	
			<body><h1>회원 Info</h1>
			<form action='update' method='post'>
			Number: <input type='text' name='no' value='${member.no }' readonly><br>
			이름: <input type='text' name='name'  value='${member.name}'><br>
			이메일: <input type='text' name='email' value='${member.email}'><br>
			join date:${member.createDate} <br>
			<input type='submit' value='Save'>
			<input type='button' value='삭제' onclick="location.href='delete?no=${member.no}'">
			<input type='button' value='취소' onclick="location.href='list'">	
			</form>
			</body></html>
</body>
</html>