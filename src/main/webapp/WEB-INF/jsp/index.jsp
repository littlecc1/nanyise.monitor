<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--
<link href="/static/css/index.css" rel="stylesheet">
<script type="text/javascript" src="/static/jars/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/static//js/index.js"></script>
--%>
<style type="text/css">
	body,table{
		font-size:12px;
	}
	table{
		table-layout:fixed;
		empty-cells:show;
		border-collapse: collapse;
		margin:0 auto;
	}
	td{
		height:20px;
	}
	h1,h2,h3{
		font-size:12px;
		margin:0;
		padding:0;
	}

	.title { background: #FFF; border: 1px solid #9DB3C5; padding: 1px; width:90%;margin:20px auto; }
	.title h1 { line-height: 31px; text-align:center;  background: #2F589C; background-repeat: repeat-x; background-position: 0 0; color: #FFF; }
	.title th, .title td { border: 1px solid #CAD9EA; padding: 5px; }


	/*这个是借鉴一个论坛的样式*/
	table.t1{
		border:1px solid #cad9ea;
		color:#666;
	}
	table.t1 th {
		background-repeat:repeat-x;
		height:30px;
	}
	table.t1 td,table.t1 th{
		border:1px solid #cad9ea;
		padding:0 1em 0;
	}
	table.t1 tr.a1{
		background-color:#f5fafe;
	}

</style>
<script type="text/javascript">
    function ApplyStyle(s){
        document.getElementById("mytab").className=s.innerText;
    }
</script>
</head>
<body>
<div class="title">
	<h1>MAIL配置信息</h1>
</div>
<table width="90%" id="mytab1"  border="1" class="t1">
	<thead>
	<th width="20%">smtp_host</th>
	<th width="30%">发送发</th>
	<th width="20%">smtp 服务密码</th>
	<th width="30%">接收方</th>
	</thead>
	<tr class="a1">
		<td><%=request.getSession().getAttribute("mail.smtp_host") %></td>
		<td><%=request.getSession().getAttribute("mail.username") %></td>
		<td><%=request.getSession().getAttribute("mail.password") %></td>
		<td><%=request.getSession().getAttribute("mail.to") %></td>
	</tr>
</table>
<div class="title">
	<h1>TOMCAT与FTP接口配置信息</h1>
</div>
<table width="90%" id="mytab2"  border="1" class="t1">
	<thead>
	<th width="50%">TOMCAT接口信息</th>
	<th width="50%">接口信息</th>
	</thead>
	<tr class="a1">
		<td><%=request.getSession().getAttribute("ACTIVEURL_SYS") %></td>
		<td><%=request.getSession().getAttribute("ACTIVEURL_FTP") %></td>
	</tr>
</table>
<div class="title">
	<h1>MYSQL配置信息</h1>
</div>
<table width="90%" id="mytab3"  border="1" class="t1">
	<thead>
	<th width="50%">JDBC路径</th>
	<th width="20%">JDBC DRIVER</th>
	<th width="15%">用户名</th>
	<th width="15%">密码</th>
	</thead>
	<tr class="a1">
		<td><%=request.getSession().getAttribute("jdbc.url") %></td>
		<td><%=request.getSession().getAttribute("jdbc.driverClassName") %></td>
		<td><%=request.getSession().getAttribute("jdbc.username") %></td>
		<td><%=request.getSession().getAttribute("jdbc.password") %></td>
	</tr>
</table>
</body>
</html>