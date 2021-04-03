<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含base标签 css样式 jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function(){
			/*删除确认*/
			$(".del_class").click(function(){
				return confirm("确认要删除商品吗");
			});
			/*更改商品项的数量*/
			$(".updateCount").change(function(){
				var count = $(this).attr("value");
				if (count<0){
					alert("请输入正整数！");
					return false;
				}
				if(confirm("确认要更改商品数量为"+ count +"吗")){
					var id = $(this).attr("BookId");
					location.href = "${pageScope.basePath}cartServlet?action=updateCount&id="+id+"&count="+count;
				}
			});
		});

	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%--静态包含 登陆成功之后的菜单--%>
			<%@ include file="/pages/common/login_success_menu.jsp"%>
	
	</div>
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<%--如果购物车为空的情况--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！</a>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="item">
				<tr>
					<td>${item.value.name}</td>
					<%--输入完新的商品数量之后，就调用响应，转到servlet的updateCount方法，更新购物车，再返回当前页面--%>
					<td><input type="text" class="updateCount" name="count" style="width: 30px" BookId="${item.value.id}" value="${item.value.count}"></td>
					<td>${item.value.price}</td>
					<td>${item.value.totalPrice}</td>
					<td><a class="del_class" href="cartServlet?action=deleteItem&id=${item.value.id}">删除</a></td>
				</tr>
				</c:forEach>
			</c:if>
		</table>
		<%--如果购物车不为空--%>
		<c:if test="${not empty sessionScope.cart.items}">
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a href="cartServlet?action=clear">清空购物车</a></span>
			<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
		</div>
		</c:if>
	</div>
	<%--静态包含页脚内容--%>
<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>