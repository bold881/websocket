package com.websystique.springmvc.socket;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class InfoSocketEndPoint extends TextWebSocketHandler {
	
	
	public InfoSocketEndPoint() {
		
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);	
	}
}
