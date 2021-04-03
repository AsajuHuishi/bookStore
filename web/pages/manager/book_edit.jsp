<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
	<%--静态包含base标签 css样式 jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="/static/img/logo.gif" >
			<span class="wel_word">编辑图书 </span>
			<%--静态包含manager管理模块菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
		</div>
		
		<div id="main">
			<form action="manager/bookServlet" method="get"><%--提交之后重新显示所有书籍--%>
				<input type="hidden" name="action" value="${requestScope.flag=='update'?'update':'add'}"/><%--判断是添加还是修改--%>
				<input type="hidden" name="id" value="${requestScope.book.id}"><%--按id修改时使用--%>
				<input type="hidden" name="pageNo" value="${param.pageNo}"><%--保存的当前页面值--%>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.book.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.book.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.book.author}"/></td>
						<td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>

		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>