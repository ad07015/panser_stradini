<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<title>Piereģistrēt rezidentu</title>
</head>

<h1>
	<a href="/resdb/">Rezidentu uzskaites sistēma</a>
</h1>
<c:out value="${message}" escapeXml="false" />

<head>
<title>New Person Success</title>
</head>
<body>
	Welcome, ${person.name}! You chose
	${person.favoriteProgrammingLanguage} as your favorite programming
	language. Therefore, we can recommend you check out ${suggestedBook}.
</body>
</html>