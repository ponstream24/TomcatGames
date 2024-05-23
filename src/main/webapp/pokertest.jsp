<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="poker.PokerModel"%>
<%@ page import="java.util.LinkedHashSet"%>

<%
PokerModel model = new PokerModel();

LinkedHashSet<Integer[]> list = new LinkedHashSet<Integer[]>();

list.add(new Integer[]{0, 9, 10, 11, 12});
list.add(new Integer[]{0, 1, 2, 3, 4});
list.add(new Integer[]{0, 0, 0, 0, 26});
list.add(new Integer[]{0, 0, 0, 1, 1});
list.add(new Integer[]{6, 7, 0, 1, 9});
list.add(new Integer[]{26, 1, 41, 29, 4});
list.add(new Integer[]{0, 0, 0, 15, 17});
list.add(new Integer[]{0, 0, 18, 18, 50});
list.add(new Integer[]{0, 0, 15, 17, 18});
list.add(new Integer[]{15, 17, 20, 34, 36});
list.add(new Integer[]{6, 7, 10, 13, 14});
list.add(new Integer[]{1, 15, 18, 38, 42});

%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>役の判別</title>
	
	<link rel="stylesheet" href="css/common.css">
</head>
<body>
	<%
	for (Integer[] card : list) {
		model.reset();
		model.nextgame();
		model.setHandcards(card[0], card[1], card[2], card[3], card[4]);
		model.evaluate();
%>
	<img src="cards/c2.png" data-src="cards/<%=model.getHandcardAt(0)%>.png" width="60"
		height="90">
	<img src="cards/c2.png" data-src="cards/<%=model.getHandcardAt(1)%>.png" width="60"
		height="90">
	<img src="cards/c2.png" data-src="cards/<%=model.getHandcardAt(2)%>.png" width="60"
		height="90">
	<img src="cards/c2.png" data-src="cards/<%=model.getHandcardAt(3)%>.png" width="60"
		height="90">
	<img src="cards/c2.png" data-src="cards/<%=model.getHandcardAt(4)%>.png" width="60"
		height="90">
	<%=model.getMessage()%><br>
	<%
	}
	%>
	
	<hr>
	<%
	for (int i = 0; i < 5; i++) {
	    model.reset();
	    model.nextgame();
	    model.evaluate();
	%>
	<img src="cards/c2.png" data-src="cards/<%=model.getHandcardAt(0)%>.png" width="60"
		height="90">
	<img src="cards/c2.png" data-src="cards/<%=model.getHandcardAt(1)%>.png" width="60"
		height="90">
	<img src="cards/c2.png" data-src="cards/<%=model.getHandcardAt(2)%>.png" width="60"
		height="90">
	<img src="cards/c2.png" data-src="cards/<%=model.getHandcardAt(3)%>.png" width="60"
		height="90">
	<img src="cards/c2.png" data-src="cards/<%=model.getHandcardAt(4)%>.png" width="60"
		height="90">
	<%=model.getMessage()%><br>
	<%
	}
	%>
	
	<script src="script/main.js"></script>
	<script type="text/javascript">
		
	start(true);
		
	</script>
</body>
</html>