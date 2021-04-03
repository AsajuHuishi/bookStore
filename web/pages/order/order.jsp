<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%--静态包含base标签 css样式 jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
	<script type="text/javascript">
		$(function (){/*签收*/
			$(".receiveOrderBtn").click(function (){
				var orderId = $(this).attr("orderId");
				var status = $(this).attr("status");
				var document = $(this);
				alert("status"+status);
				$.getJSON("http://localhost:8080/07_book/orderServlet","action=receiveOrder&orderId="+orderId+"&status="+status,
						function(data){document.parent().html(data)
				});
			});

		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
		<%--静态包含 登陆成功之后的菜单--%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${requestScope.pageOrder.items}" var="order">
				<tr>
					<td><%--只输出date--%>
						<fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd" ></fmt:formatDate>
					</td>
					<td>${order.price}</td>
					<td><span>
						<c:if test="${order.status==0}">未发货</c:if>
						<c:if test="${order.status==1}">
							已发货  <input type="button" orderId="${order.orderId}" status="${order.status}" class="receiveOrderBtn" value="签收"/>
						</c:if>
						<c:if test="${order.status==2}">已签收</c:if>
						</span>
					</td>
					<td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
				</tr>
			</c:forEach>

		</table>
		<%--分页--%>
		<%@include file="/pages/common/page_order_naiv.jsp"%>
	
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>