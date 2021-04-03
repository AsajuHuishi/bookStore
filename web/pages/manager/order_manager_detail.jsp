<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%--静态包含base标签 css样式 jquery文件--%>
    <%@include file="/pages/common/head.jsp"%>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">订单管理系统</span>
    <%--静态包含manager管理模块菜单--%>
    <%@include file="/pages/common/manager_menu.jsp"%>
</div>
${requestScope.orders}
<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>金额</td>
            <td>数量</td>
            <td>总金额</td>
            <td>订单号</td>
        </tr>
        <c:forEach items="${requestScope.orderItems}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.count}</td>
                <td>${book.totalPrice}</td>
                <td>${book.orderId}</td>
            </tr>
        </c:forEach>

    </table>

</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>