<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <c:if test="${!empty page.data }">
	<div id="page_nav">
			<a href="${page.path }&pageNumber=1">首页</a>
			<a href="${page.path }&pageNumber=${page.pageNumber-1 }">上一页</a>
			
			<%--指定两个变量begin 和 end --%>
			<%--1.总页数小于5 --%>
			<%--2.当前页小于等于3 --%>
			<%--3.当前页大于3 --%>
			<%-- 如果end大于totalPage，则end=totalPage begin=end-4 --%>
			<c:choose>
				<c:when test="${page.totalPage < 5 }">
					<c:set var="begin" value="1" ></c:set>
					<c:set var="end" value="${page.totalPage }" ></c:set>
				</c:when>
				<c:when test="${page.pageNumber <= 3 }">
					<c:set var="begin" value="1" ></c:set>
					<c:set var="end" value="5" ></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="begin" value="${page.pageNumber-2 }" ></c:set>
					<c:set var="end" value="${page.pageNumber+2 }" ></c:set>
					<c:if test="${end > page.totalPage }">
						<c:set var="end" value="${page.totalPage }"></c:set>
						<c:set var="begin" value="${end-4 }"></c:set>
					</c:if>
				</c:otherwise>
			</c:choose>
			
			<c:forEach begin="${begin }" end="${end}" var="index">
				
				<c:choose>
					<c:when test="${index == page.pageNumber }">
						<span style="color: red">[${index }]</span>
					</c:when>
					<c:otherwise>
						<a href="${page.path }&pageNumber=${index }">${index }</a>
					</c:otherwise>
				</c:choose>
			
			</c:forEach>
			
			<a href="${page.path }&pageNumber=${page.pageNumber+1 }">下一页</a>
			<a href="${page.path }&pageNumber=${page.totalPage }">末页</a>
			共${page.totalPage }页，${page.totalRecord }条记录 到第<input value="${page.pageNumber }" name="pn" id="pn_input"/>页
			<input type="button" value="确定" id="pn_btn">
			<script type="text/javascript">
				//为确定按钮绑定单击响应函数
				$("#pn_btn").click(function(){
					
					//获取用户填写的页码
					var pn = $("#pn_input").val();
					
					//跳转页面
					//window.location用于操作浏览器跳转到另一个页面
					window.location = "${page.path }&pageNumber="+pn;
					
				});
			
			</script>
		</div>
		</c:if>