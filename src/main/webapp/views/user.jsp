<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
  </head>

  <body>
    id:${requestScope.user.id}<br/>
    email:${requestScope.user.email}<br/>
    userName:${requestScope.user.username}<br/>
    roles:${requestScope.user.roles}<br/>
    mobile:${requestScope.user.mobile}<br/>
  </body>
</html>