<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {
			//删除一键商品需要提示用户是否删除
			$(".deleteItem").click(function () {
				return confirm("你确定要删除【"+ $(this).parent().parent().find("td:first").text() +"】吗？")
			})

			//清空购物车需要提示用户是否要清空
			$("#clearCart").click(function () {
				return confirm("你确定要清空购物车吗？")
			})

			//提示用户是否要修改数量
			$(".updatecount").change(function () {
				// 获取商品名称
				var name = $(this).parent().parent().find("td:first").text();

				var id = $(this).attr("bookId")

				//这里的this.value是获取class为updatecount的input标签中的value属性设置默认值
				var count1 = this.value;

				if (confirm("你确定要将【"+ name +"】的数量修改"+ count1 +"吗"))
				{
					location.href = "cartServlet?action=update&count=" + count1 + "&id=" + id;
				}
				else
				{
					this.value = this.defaultValue;
				}
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/pages/common/div_welcome.jsp"%>
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


			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空，快和小伙伴去浏览商品页面把！！！</a></td>
				</tr>
			</c:if>

			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="item">
					<tr>
						<td>${item.value.name}</td>
						<td>
							<input bookId="${item.value.id}" class="updatecount" style="width: 50px" type="text" value="${item.value.count}">
						</td>
						<td>3${item.value.price}</td>
						<td>${item.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=delete&id=${item.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>

		</table>


		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalItem}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>