<%-- 
    Document   : courses
    Created on : 2019-11-24, 11:47:12
    Author     : c
--%>

<%@page import="fr.utbm.entity.Course"%>
<%@page import="fr.utbm.entity.Location"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="http://g.alicdn.com/sj/dpl/1.5.1/css/sui.min.css"
              rel="stylesheet">
    </head>
    <body>
        <h1>是你掉的这些课吗？</h1>

        <div class="sui-container">
            <form class="sui-form form-search" action="searchName" method="post">
                <input type="text" class="input-medium search-query" name="title">
                <button type="submit" class="sui-btn btn-info">查询</button>
            </form>
        </div>
         <div class="sui-container">
            <form class="sui-form form-search" action="searchMultiCondition" method="post">
                Part of Title: <input type="text" class="input-medium search-query" name="title">
                Start Date: <input type="text" class="input-medium search-query" name="dateStart">
                Start Date: <input type="text" class="input-medium search-query" name="dateEnd">
                Choose one location: <input type="text" class="input-medium search-query" name="locationId">
                <button type="submit" class="sui-btn btn-info">查询</button>
            </form>
        </div>
        <div class="sui-container">
            <table class="sui-table table-primary">
                <thead>

                    <tr>
                        <th>编号</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="oneuser" items="${users}">
                        <tr>
                            <td>${oneuser.id}</td>
                            <td>${oneuser.title}</td>
                            <c:forEach var="courseSession" items="${oneuser.courseSessionCollection}">
                            <td>${courseSession.startDate}</td>
                            <td>${courseSession.endDate}</td>
                            <td>${courseSession.maxNumber}</td>
                            <td>${courseSession.locationId.city}</td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </body>
    <script type="text/javascript" src="http://g.alicdn.com/sj/lib/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="http://g.alicdn.com/sj/dpl/1.5.1/js/sui.min.js"></script>
</html>
