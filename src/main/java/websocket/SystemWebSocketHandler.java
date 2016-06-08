package websocket;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
@Component
public class SystemWebSocketHandler implements WebSocketHandler{

	private static final ArrayList<WebSocketSession> users;

	static{
		users = new ArrayList();
	}
	
	public SystemWebSocketHandler(){
		
	}

	/**
	 * 删除客户端的连接
	 * @param session
	 * @param status
	 * @throws Exception
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
			throws Exception {
		users.remove(session);
	}

	/**
	 * 保存客户端的连接信息
	 * @param session
	 * @throws Exception
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		System.out.println("Server:cennected OK!");
		users.add(session);
	}

	/**
	 * 接收客户端发来的消息
	 * @param session
	 * @param message
	 * @throws Exception
	 */
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage message)
			throws Exception {
		TextMessage tm = new TextMessage(message.getPayload()+"");
		System.out.println(message.getPayload());
		session.sendMessage(tm);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable throwable)
			throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给所有在线用户发送消息
	 * @param message
	 */
	public void sendMessageToUsers(TextMessage message) {
		for (WebSocketSession user : users) {
			if (user.isOpen()) {
				try {
					user.sendMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void sendMessageToUser(String username,TextMessage message) {
		for (WebSocketSession user : users) {
			if (user.getHandshakeAttributes().get("username").equals(username)) {
				try {
					if (user.isOpen()) {
						user.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}