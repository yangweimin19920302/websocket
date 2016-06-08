<%@ page language="java" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>hi</title>

</head>

<body>
交流：
<span id="msgcount"></span>
<form action="${pageContext.request.contextPath}/send">

  <textarea name="message" rows="2" cols="30"></textarea>
  <input type="submit">
</form>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/jquery-1.11.1.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/sockjs-0.3.min.js"></script>
<script type="text/javascript">
  var websocket;
  if ('WebSocket' in window) {
    websocket = new WebSocket("ws://localhost:8081/tw/webSocketServer");
  } else {
    websocket = new SockJS("http://localhost:8081/tw/sockjs/webSocketServer");
  }
  websocket.onopen = function (evnt) {
  };
  websocket.onmessage = function (evnt) {
    $("#msgcount").html("(<font color='red'>"+evnt.data+"</font>)")
  };
  websocket.onerror = function (evnt) {
  };
  websocket.onclose = function (evnt) {
  }
</script>
</body>
</html>