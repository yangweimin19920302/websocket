<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>Java API for WebSocket (JSR-356)</title>
</head>
<body>
<script type="text/javascript" charset="UTF-8" src="jquery-1.11.1.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="sockjs-0.3.min.js"></script>
<script type="text/javascript">
  var websocket = null;
  if ('WebSocket' in window) {
    websocket = new WebSocket("ws://localhost:8081/webSocketServer");
  }
  else if ('MozWebSocket' in window) {
    websocket = new MozWebSocket("ws://localhost:8081/webSocketServer");
  }
  else {
    websocket = new SockJS("http://localhost:8081/sockjs/webSocketServer");
  }
  websocket.onopen = onOpen;
  websocket.onmessage = onMessage;
  websocket.onerror = onError;
  websocket.onclose = onClose;

  function onOpen(openEvt) {
    //alert(openEvt.Data);
  }

  function onMessage(evt) {
    alert(evt.data);
  }
  function onError() {}
  function onClose() {}

  function doSend() {
    if (websocket.readyState == websocket.OPEN) {
      var msg = document.getElementById("inputMsg").value;
      websocket.send(msg);//调用后台handleTextMessage方法
      alert("发送成功!");
    } else {
      alert("连接失败!");
    }
  }
</script>
请输入：<textarea rows="5" cols="10" id="inputMsg" name="inputMsg"></textarea>
<button onclick="doSend();">发送</button>
</body>
</html>