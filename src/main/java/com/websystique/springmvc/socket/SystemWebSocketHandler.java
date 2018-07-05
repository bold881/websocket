package com.websystique.springmvc.socket;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class SystemWebSocketHandler implements WebSocketHandler {
	private static ArrayList<WebSocketSession> allSessions = new ArrayList<WebSocketSession>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("connect to the websocket success......");
	    session.sendMessage(new TextMessage("Server:connected OK!"));
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//		TextMessage returnMessage = new TextMessage(message.getPayload()
//				  + " received at server");
//		session.sendMessage(returnMessage);
		if(!allSessions.contains(session)) {
			allSessions.add(session);
		}
		TextMessage returnMessage = new TextMessage((CharSequence) message.getPayload());
		System.out.println("session count: " + allSessions.size());
		for(WebSocketSession s: allSessions) {
			if(s == session) {
				continue;
			}
			System.out.println("dealing " + s.getLocalAddress().toString());
			if(s.isOpen()) {
				s.sendMessage(returnMessage);
			} else {
				allSessions.remove(s);
			}
		}	
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if(session.isOpen()){
	           session.close();
	       }
	      System.out.println("websocket connection closed......");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.out.println("websocket connection closed......");

	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
