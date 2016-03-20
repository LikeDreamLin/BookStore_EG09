<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		//为id为clear_btn的a绑定一个单级响应函数
		$("#clear_btn").click(function(){
			
			//弹出一个确认框
			if(!confirm("是否要清空购物车！")){
				//取消默认行为
				return false;
			}
			
			
		});
		
		//为class为delA的超链接绑定一个单击响应函数
		$(".delA").click(function(){
			
			//获取要删除的图书的名字
			var title = $(this).parents("tr").find("td:eq(0)").text();
			
			//弹出一个确认框
			if(!confirm("确认删除《"+title+"》吗?")){
				//取消默认行为
				return false;
			}
			
			
		});
		
		//为.count_input绑定一个change事件
		$(".count_input").change(function(){
			
			
			//获取用户填写的数量
			var count = this.value;
			
			//检查用户填写数量是否合法，大于0的整数
			var reg = /^\+?[1-9][0-9]*$/;
			
			
			
			//如果字符串不符合规则
			if(!reg.test(count)){
				
				//将value设置为默认值
				this.value = this.defaultValue;
				
				alert("请输入一个有效的数量！");
			}else{
				
				//获取库存
				var stock = this.name;
				
				//将stock转换为一个number
				stock = new Number(stock);
				
				//判断数量是否大于库存
				if(count > stock){
					//将数量设置为默认值
					this.value = this.defaultValue;
					
					//弹出一个提示框
					alert("购买的数量超过最大库存！");
					
					return;
				}				
				
				//count是正确值
				//获取要修改购物项的id
				var bookId = this.id;
				
				//创建一个url地址
				var url = "${pageContext.request.contextPath}/client/CartServlet";
				//准备请求参数
				var param = {
						"method":"updateCount",
						"bookId":bookId,
						"count":count
				};
				
				//获取小计金额的td
				var $td = $(this).parents("tr").find("td:eq(3)");
				
				//发送请求
				$.post(url,param,function(data){
					
					//获取总数量
					var totalCount = data.totalCount;
					//获取总金额
					var totalAmount = data.totalAmount;
					//获取小计金额
					var amount = data.amount;
					
					//修改总数量
					$(".b_count").html(totalCount);
					//修改总金额
					$(".b_price").html(totalAmount);
					//修改小计金额
					$td.html(amount);
					
					
					
				},"json");
				
				
			}
			
			
		});
		
		
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@ include file="/WEB-INF/include/user-info.jsp" %>
	</div>
	
	<div id="main">
	
		<c:choose>
			<c:when test="${empty cart.cartItems }">
				<br /><br /><br /><br /><br /><br /><br />
				<h1 align="center">购物车中还没有商品，赶快去购物吧！</h1>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>商品名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>金额</td>
						<td>操作</td>
					</tr>	
					<c:forEach items="${cart.cartItems }" var="item">
						<tr>
							<td>${item.book.title }</td>
							<td><input name="${item.book.stock }" id="${item.book.id }" class="count_input" type="text" value="${item.count }" style="width: 30px; text-align: center;" /></td>
							<td>${item.book.price }</td>
							<td>${item.amount }</td>
							<td><a class="delA" href="client/CartServlet?method=del&bookId=${item.book.id}">删除</a></td>
						</tr>	
					</c:forEach>	
				</table>
				
				<div class="cart_info">
					<span class="cart_span">购物车中共有<span class="b_count">${cart.totalCount }</span>件商品</span>
					<span class="cart_span">总金额<span class="b_price">${cart.totalAmount }</span>元</span>
					<span class="cart_span"><a id="clear_btn" href="client/CartServlet?method=clear">清空购物车</a></span>
					<span class="cart_span"><a href="client/OrderClientServlet?method=checkout">去结账</a></span>
				</div>
		
			</c:otherwise>
		</c:choose>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>