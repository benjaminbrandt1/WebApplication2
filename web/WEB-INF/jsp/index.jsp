<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        <h1>Welcome!</h1>
        <h2>
            Add a User
        </h2>
        <% String formAction = "insert.htm"; %>
        <c:if test="${!empty user.firstName}">
            <% formAction = "update.htm"; %>
        </c:if>
        <form:form action="<%=formAction%>" method="POST" modelAttribute="user">
            <table>

                <tr>
                    <td>
                        <form:label path="firstName">
                            <spring:message text="firstName"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="firstName" value="${user.firstName}" />
                    </td> 
                </tr>
                <tr>
                    <td>
                        <form:label path="lastName">
                            <spring:message text="lastName"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="lastName" value="${user.lastName}" />
                    </td> 
                </tr>
                <tr>
                    <td>
                        <form:label path="country">
                            <spring:message text="Country"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="country" value="${user.country}" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="pswd">
                            <spring:message text="pswd"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="pswd" type="password" value="${user.pswd}" />
                    </td> 
                </tr>
                <tr>
                    <td>
                        <form:label path="email">
                            <spring:message text="email"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="email" value="${user.email}" />
                    </td> 
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${!empty user.firstName}">
                            <input type="submit"
                                   value="<spring:message text="Edit User"/>" />
                        </c:if>
                        <c:if test="${empty user.firstName}">
                            <input type="submit"
                                   value="<spring:message text="Add User"/>" />
                        </c:if>
                    </td>
                </tr>
            </table>	
        </form:form>
        <br>
        <h3>User List</h3>
        <c:if test="${!empty listUsers}">
            <table class="tg">
                <tr>
                    <th width="80">User ID</th>
                    <th width="120">First Name</th>
                    <th width="120">Last Name</th>
                    <th width="120">Country</th>
                    <th width="120">Password</th>
                    <th width="120">Email</th>
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </tr>
                <c:forEach items="${listUsers}" var="user">
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.country}</td>
                        <td>${user.pswd}</td>
                        <td>${user.email}</td>
                        <td><a href="<c:url value='/edit/${user.userId}.htm' />" >Edit</a></td>
                        <td><a href="<c:url value='/remove/${user.userId}.htm' />" >Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>