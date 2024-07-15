<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="db.Appear, java.util.List"%>
<%@ page import="static db.AppearDAO.*"%>
<%
List<Appear> appearList = (List<Appear>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/appear.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link
	href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
</head>
<body>
	<h1>
		<a href="AppearServlet">ポケモン出現DB</a>
	</h1>
	<hr>
	<form action="AppearServlet" method="POST">

		<div class="box">
			<h3>並び替え(JavaScript側で制御しているので、もう機能しません。)</h3>
			<label> <input type="radio" name="item" value="ID"
				checked="checked">ID
			</label> <label> <input type="radio" name="item" value="APPEAR.番号">番号
			</label> <label> <input type="radio" name="item" value="名前">名前
			</label> <br /> <label> <input type="radio" name="order" value="asc"
				checked="checked">昇順
			</label> <label> <input type="radio" name="order" value="desc">降順
			</label> <br /> <input type="submit" name="submit" value="並び替え">

		</div>
		<hr>
		<div class="box">
			<h3>新規追加</h3>
			<p>空白でランダムです</p>
			番号<input type="text" name="newnumber"> 市コード<input type="text"
				name="newshicode"> <input type="submit" name="submit"
				value="登録">

		</div>
		<hr>
		<div class="box">
			<h3>削除</h3>
			ID<input type="text" name="deleteid"> <input type="submit"
				name="submit" value="削除">

		</div>
	</form>

	<hr>
	<div class="box">
		<h3>絞り込み(タイプ)</h3>
		<div class="shimei_set">
			<%
			for (String type : getTypeList()) {
			%>

			<form action="AppearServlet" method="POST">
				<input type="submit" name="type" value="<%=type%>">
			</form>
			<%
			}
			%>
			<form action="AppearServlet" method="POST">
				<input type="hidden" name="type" value="null"> <input
					type="submit" value="全部">
			</form>
		</div>
	</div>
	<div class="box">
		<hr>
		<h3>絞り込み(市)</h3>
		<div class="shimei_set">
			<%
			for (String shi : getShimeiList()) {
			%>

			<form action="AppearServlet" method="POST">
				<input type="submit" name="shimei" value="<%=shi%>">
			</form>
			<%
			}
			%>
			<form action="AppearServlet" method="POST">
				<input type="hidden" name="shimei" value="null"> <input
					type="submit" value="全部">
			</form>
		</div>
	</div>
	<hr>
	<div class="box">
		<%
		if (appearList != null) {
		%>
		<h3>出現情報</h3>
		<table id="dataTable" class="display tbl-r04">
			<thead>
				<tr>
					<th>ID</th>
					<th>番号</th>
					<th>名前</th>
					<th>県名</th>
					<th>市名</th>
					<th>日付</th>
					<th>時刻</th>
					<th>タイプ</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Appear appear : appearList) {
				%>
				<tr class="">
					<td><%=appear.getId()%></td>
					<td><%=appear.getNumber()%></td>
					<td><%=appear.getName()%></td>
					<td><%=appear.getKen()%></td>
					<td><%=appear.getShi()%></td>
					<td><%=appear.getDate()%></td>
					<td><%=appear.getTime()%></td>
					<td>
						<%
						for (String type : appear.getType()) {
						%> 
							<%=type%>, 
						<%
						}
						 %>
					</td>
					<td>
						<form action="AppearServlet" method="POST">
							<input type="hidden" name="deleteid" value="<%=appear.getId()%>">
							<input type="submit" name="submit" value="削除">
						</form>
					</td>
				</tr>
				<%
				}
				%>
			
		</table>
		<%
		}
		%>

	</div>

	<script type="text/javascript">
		$(function() {
			var table = $('#dataTable')
					.DataTable(
							{
								language : {
									url : "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
								},
								stateSave : true,
								displayLength : 25,
								paging : true,
								ordering : true,
								mark : true,
								autoWidth : false
							});
		});
	</script>
</body>
</html>
