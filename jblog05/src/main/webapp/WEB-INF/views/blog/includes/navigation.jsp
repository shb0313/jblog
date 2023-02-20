<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div id="navigation">
	<h2>카테고리</h2>
	<ul>
		<c:forEach items="${listCategory }" var="vo" varStatus="status">
			<li><a href="${pageContext.request.contextPath }/${blogVo.id }/${vo.no }">${vo.name }</a></li>
		</c:forEach>
	</ul>
</div>
