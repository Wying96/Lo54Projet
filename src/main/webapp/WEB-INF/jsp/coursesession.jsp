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
<html lang = "en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FormationEcole Courses</title>
        <link href="http://g.alicdn.com/sj/dpl/1.5.1/css/sui.min.css"
              rel="stylesheet">
    </head>
    <body style="text-align:center;">
        <h1 align="center">Cours</h1>
        <c:if test="${empty user}"> <div class="sui-container">

                <button class="sui-btn btn-success"><a href="inscrire">s'inscrire</a></button></c:if>
            <c:if test="${not empty user}"> <div class="sui-container">
                    <h4 align="center"> Bienvenue : ${user.email}</h4>
                </div></c:if>

                <div class="sui-container">
                    <form class="sui-form form-search" action="searchMultiCondition" method="post" >
                        Part of Title: <input type="text" class="input-medium search-query" name="title">
                        <input type='hidden' readonly="true" name='email' value="${user.email}">
                        <span> Start After Date：</span><input type="date" name="date1" data-date-format="YYYY-MM-DD" lang="en"/>
                        <span> End Before Date：</span><input type="date" name="date2" pattern="YYYY-DD-MM" lang="en"/>
                        Choose one location:                 
                        <select name="city" id="cityName" > 
                        <c:forEach var="cityname" items="${lists}"> 
                            <option value="${cityname.id}">${cityname.city}</option> 
                        </c:forEach> 
                    </select> 
                    <button type="submit" class="sui-btn btn-info">chercher</button>
                </form>
            </div>
            <div class="sui-container">
                <table class="sui-table table-primary">
                    <thead>
                        <tr>
                            <th>Code</th>
                            <th>Title</th>
                            <th>N° session</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>nombre/nombre maximale</th>
                            <th>city</th>
                            <th>operation</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="oneCourse" items="${coursesList}">
                            <tr>
                                <c:if test="${oneCourse.courseSessionCollection.size() ==0}">
                                    <td><c:out value="${oneCourse.id}"/></td>
                                    <td><c:out value="${oneCourse.title}"/></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>        
                            </c:if>


                            <c:forEach var="courseSession" items="${oneCourse.courseSessionCollection}">
                                <tr>
                                    <td>${oneCourse.id}</td>
                                    <td>${oneCourse.title}</td>
                                    <td>${courseSession.id}</td>
                                    <td>${courseSession.startDate}</td>
                                    <td>${courseSession.endDate}</td>
                                    <td>${courseSession.usersCollection.size()}/${courseSession.maxNumber}</td>

                                    <td>${courseSession.locationId.city}</td>
                                    <td>
                                        <c:if test="${!user.courseSessionCollection.contains(courseSession)}">
                                            <form action="inscrireCourse" method="post">
                                                <input type="hidden" name="email" readonly="true" value="${user.email}"/>
                                                <input type="hidden" name="courseSessionId" readonly="true" value="${courseSession.id} "/>
                                                <button class="sui-btn btn-success" type="submit" id="btn_preInscrir">Pré-inscrir</button>
                                            </form>
                                        </c:if>
                                        <c:if test="${user.courseSessionCollection.contains(courseSession)}">
                                            Vous avez bien pré-inscrire ce course!
                                        </c:if>
                                    </td>
                                    </form>
                                </tr>

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
