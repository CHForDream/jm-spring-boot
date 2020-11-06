package com.game.core.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(websocket(), "/ws/websocket").setAllowedOrigins("*").withSockJS();
		registry.addHandler(webchat(), "/ws/webchat").setAllowedOrigins("*").withSockJS();
	}

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	@Bean
	public WebSocketHandler websocket() {
		return new BattleWebSockethandler();
	}

	@Bean
	public WebSocketHandler webchat() {
		return new ChatWebSockethandler();
	}

}