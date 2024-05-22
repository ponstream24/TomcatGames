<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="poker.PokerModel"%>
<%@ page import="java.util.List"%>
<%
PokerModel model = (PokerModel) request.getAttribute("model");
String label = model.getButtonLabel();

String disabled = "disabled";
if (label.equalsIgnoreCase("交換")) {
	disabled = "";
}

String koukan = "交換";

List<String> list = (List<String>) request.getAttribute("changelist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Poker</title>
<link rel="stylesheet" href="css/common.css">
</head>
<body>
	<header> 
		<div class="title">ポーカーゲーム</div>
		<div class="count">ゲーム回数：<%=model.getGames()%></div>
		<div class="chip"><%=model.getChips()%></div>
	</header>
	<hr>
	<%
	if (label.equalsIgnoreCase(koukan)) {
	%>
	<div id="message" style="opacity: 1"><%=model.getMessage()%></div>
	<%
	} else {
	%>

	<div id="message" style="opacity: 0"><%=model.getMessage()%></div>
	<%
	}
	%>
	<form action="/s2232103/PokerServlet" method="POST">
		<table>
			<tr>
				<%
				for (int i = 0; i < 5; i++) {
				%>
				<%
				if (label.equalsIgnoreCase(koukan) || (list != null && list.contains(i + ""))) {
				%>
				<td><label for="change_<%=i%>"> <img src="cards/c1.png"
						data-src="cards/<%=model.getHandcardAt(i)%>.png" width="100"
						height="150"> <input type="checkbox" name="change"
						onclick="playdrowSound()" value="<%=i%>" id="change_<%=i%>"
						<%=disabled%>>
				</label></td>
				<%
				} else {
				%>
				<td><label for="change_<%=i%>"> <img class="open"
						src="cards/<%=model.getHandcardAt(i)%>.png" width="100"
						height="150"> <input type="checkbox" name="change"
						onclick="playdrowSound()" value="<%=i%>" id="change_<%=i%>"
						<%=disabled%>>
				</label></td>
				<%
				}
				%>
				<%
				}
				%>
			</tr>
		</table>
		<input id="bot" type="submit" value="<%=label%>" style="opacity: 0">
	</form>
	
	<div id="gameOver">
		<div class="title">GAME OVER!</div>
	</div>
	<hr>
	<a href="/s2232103/">ゲーム終了</a>

	<script src="script/main.js"></script>
	<script type="text/javascript">
		
	<%if (label.equalsIgnoreCase(koukan)) {%>
		start(true);
	<%} else {%>
		start(false);
	<%}%>
		function showMessage() {

			setTimeout( () => {
				if( document.querySelectorAll("img").length == document.querySelectorAll("img.open").length ){
					document.getElementById("message").style.opacity = 1;
					document.getElementById("bot").style.opacity = 1;


					if( document.getElementById("bot").value == "次のゲーム" ){

						const payOut = new Audio();
						payOut.src = "sound/coin-donation-3-181411.mp3";
						payOut.play();

						if( Number(document.querySelector(".chip").innerText) < 0 ){
							setTimeout( () => {
								showGameOver();
							}, 500 )
						}
					}
				}
			}, 500 )
		}

		showMessage();

		function showGameOver(){
			document.getElementById("gameOver").style.display = "flex";
			document.getElementById("gameOver").addEventListener("click", ()=>{
				window.location.href = "http://localhost:8080/s2232103/"
			})
		}
	</script>
</body>
</html>