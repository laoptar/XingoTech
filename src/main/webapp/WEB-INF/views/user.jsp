<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>用户信息</title>
  </head>

  <body>
    userId:${requestScope.user.userId}<br/>
    userName:${requestScope.user.userName}<br/>
    userType:${requestScope.user.userType}<br/>
    email:${requestScope.user.email}<br/>
    mobilePhone:${requestScope.user.mobilePhone}<br/>
    sex:${requestScope.user.sex}<br/>
    empName:${requestScope.user.empName}<br/>
    empCode:${requestScope.user.empCode}<br/>
    officePhone:${requestScope.user.officePhone}<br/>
    activeDate:${requestScope.user.activeDate}<br/>
    disableDate:${requestScope.user.disableDate}<br/>
    userDesc:${requestScope.user.userDesc}<br/>
    enabled:${requestScope.user.enabled}<br/>
  </body>
</html>