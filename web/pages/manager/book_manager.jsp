<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%--静态包含base标签 css样式 jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function(){
			$(".delClass").click(function (){
				alert("huaQ");
				var flag = confirm("要删除"+$(this).parent().parent().find("td:first").text()+"?");
				return flag;
			});
			// 跳到指定的页码
			$("#searchPageBtn").click(function(){
				var page = $("#pn_input").val();
				// 判断页码 不超出范围 在PageService里检查
				<%--if(!Number.isInteger(page)|| page>${requestScope.page.pageTotal} || page<1){--%>
				<%--	alert("不合法页码");--%>
				<%--	return false;--%>
				<%--}--%>
				// js提供了跳转location href可读可写
				location.href = "${pageScope.basePath}"+"${requestScope.page.url}&pageNo="+page;
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%--静态包含manager管理模块菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=queryBookById&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="delClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
						<%--#id只能使用一次，这里实际是循环，所以要用class--%>
				</tr>
			</c:forEach>

<%--			<tr>--%>
<%--				<td>时间简史</td>--%>
<%--				<td>20.00</td>--%>
<%--				<td>霍金</td>--%>
<%--				<td>200</td>--%>
<%--				<td>400</td>--%>
<%--				<td><a href="book_edit.jsp">修改</a></td>--%>
<%--				<td><a href="#">删除</a></td>--%>
<%--			</tr>	--%>
			
<%--			<tr>--%>
<%--				<td>时间简史</td>--%>
<%--				<td>20.00</td>--%>
<%--				<td>霍金</td>--%>
<%--				<td>200</td>--%>
<%--				<td>400</td>--%>
<%--				<td><a href="book_edit.jsp">修改</a></td>--%>
<%--				<td><a href="#">删除</a></td>--%>
<%--			</tr>	--%>
<%--			--%>
<%--			<tr>--%>
<%--				<td>时间简史</td>--%>
<%--				<td>20.00</td>--%>
<%--				<td>霍金</td>--%>
<%--				<td>200</td>--%>
<%--				<td>400</td>--%>
<%--				<td><a href="book_edit.jsp">修改</a></td>--%>
<%--				<td><a href="#">删除</a></td>--%>
<%--			</tr>	--%>
<%--			--%>
<%--			<tr>--%>
<%--				<td>时间简史</td>--%>
<%--				<td>20.00</td>--%>
<%--				<td>霍金</td>--%>
<%--				<td>200</td>--%>
<%--				<td>400</td>--%>
<%--				<td><a href="book_edit.jsp">修改</a></td>--%>
<%--				<td><a href="#">删除</a></td>--%>
<%--			</tr>	--%>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageNo}">添加图书</a></td>
			</tr>
		</table>
<%--分页条--%>
        <%@include file="/pages/common/page_naiv.jsp"%>
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>