<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--静态包含base标签 css样式 jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$(".sendBtn").click(function(){/*修改发货*/
				var document = $(this);// ajax里面不能直接用$(this)
				// 点击发货
				var orderId = $(this).attr("orderId");
				var status = $(this).attr("status");
				// alert("ss"+status);
				$.getJSON("http://localhost:8080/07_book/orderServlet?action=sendOrder","orderId="+orderId+"&status="+status,function(data){
					// 传过来的是字符串
					// alert(document.parent().html())
					document.parent().html(data);//在按钮的位置写新的状态
				});
			});
		});
	</script>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif" >
		<span class="wel_word">订单管理系统</span>
		<%--静态包含manager管理模块菜单--%>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
<%--	${requestScope.orders}--%>
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>
			<c:forEach items="${requestScope.pageOrder.items}" var="order">
			<tr>
				<td>${order.createTime}</td>
				<td>${order.price}</td>
				<td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td><%--根据 订单id 查看--%>
				<td><span class="sendOrder">
					<c:if test="${order.status==0}"><%--发货--%>
						<input class="sendBtn" type="button" orderId="${order.orderId}" status="${order.status}" value="点击发货">
					</c:if>
					<c:if test="${order.status==1}">
						发货成功 待签收
					</c:if>
					<c:if test="${order.status==2}">
						已签收
					</c:if>
					</span>
				</td>
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